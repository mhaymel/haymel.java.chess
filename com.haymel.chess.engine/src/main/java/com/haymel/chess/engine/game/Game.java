/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Field.a1;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.a8;
import static com.haymel.chess.engine.board.Field.e1;
import static com.haymel.chess.engine.board.Field.e8;
import static com.haymel.chess.engine.board.Field.h1;
import static com.haymel.chess.engine.board.Field.h8;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.fen.PositionFromFEN.positionFromInitialFen;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.Border;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelException.throwHE;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.castling.CastlingRightHistory;
import com.haymel.chess.engine.castling.PositionCastlingRight;
import com.haymel.chess.engine.evaluation.BlackPiecesPositionValue;
import com.haymel.chess.engine.evaluation.PiecePositionValue;
import com.haymel.chess.engine.evaluation.PieceValue;
import com.haymel.chess.engine.evaluation.WhitePiecesPositionValue;
import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.moves.black.BlackMoves;
import com.haymel.chess.engine.moves.black.capture.BlackCaptureMoves;
import com.haymel.chess.engine.moves.white.WhiteMoves;
import com.haymel.chess.engine.moves.white.capture.WhiteCaptureMoves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class Game {	//TODO unit test and refactor

	private final PieceList whitePieces = new PieceList();
	private final PieceList blackPieces = new PieceList();
	private Piece[] board;
	private int activeColor;

	private final Move[] undos = new Move[1000];
	private int undosIndex = 0;
	
	private int enPassant;
	private final int[] enPassantStack = new int[1000];
	private int enPassantStackIndex = 0;
	
	private int halfMoveClock;
	private final int[] halfMoveClockStack = new int[(15+8*6)*2 + 100];
	private int halfMoveClockStackIndex = 0;
	
	private int fullMoveNumber;
	private final WhiteMoves whiteMoves;
	private final WhiteCaptureMoves whiteCaptureMoves;
	private final BlackMoves blackMoves;
	private final BlackCaptureMoves blackCaptureMoves;
	private int pieceValue;
	private PiecePositionValue whitePiecePositionValue;
	private PiecePositionValue blackPiecePositionValue;
	private final CastlingRightHistory castlingRightHistory = new CastlingRightHistory();
	private int whitePositionValue;
	private int blackPositionValue;
	
	public Game(Position position) {
		this(position, new WhitePiecesPositionValue(), new BlackPiecesPositionValue());
	}
	
	public Game() {
		this(positionFromInitialFen(), new WhitePiecesPositionValue(), new BlackPiecesPositionValue());
	}

	public Game(Position position, PiecePositionValue whitePiecePositionValue, PiecePositionValue blackPiecePositionValue) {
		position.verify();
		this.board = position.board();
		this.activeColor = position.activeColor();
		this.enPassant = position.enPassant();
		this.halfMoveClock = position.halfMoveClock();
		this.fullMoveNumber = position.fullMoveNumber();
		this.castlingRightHistory.castlingRight().rights(position.castlingRight());
		this.whiteMoves = new WhiteMoves(board);
		this.whiteCaptureMoves = new WhiteCaptureMoves(board);
		this.blackMoves = new BlackMoves(board);
		this.blackCaptureMoves = new BlackCaptureMoves(board);
		this.whitePiecePositionValue = nonNull(whitePiecePositionValue, "whitePiecePositionValue");
		this.blackPiecePositionValue = nonNull(blackPiecePositionValue, "blackPiecePositionValue");

		init();
		assertVerify();
	}

	public Position position() {
		return new Position(board, activeColor, enPassant, halfMoveClock, fullMoveNumber, castlingRight());
	}
	
	private void init() {
		for(int i = 0; i < board.length; i++)
			if (board[i] != null)
				add(board[i]);
	}

	private void add(Piece piece) {
		assert piece != null;
		if (PieceType.white(piece.type())) {
			whitePieces.init(piece);
			addWhite(piece);
		}
		
		if (PieceType.black(piece.type())) {
			blackPieces.init(piece);
			addBlack(piece);
		}
	}

	public void push(Move move) {
		undos[undosIndex++] = move;
		enPassantStack[enPassantStackIndex++] = enPassant;
		enPassant = removed;
	}
	
	public Move pop() {
		Move undo = undos[--undosIndex];
		enPassant = enPassantStack[--enPassantStackIndex];
		return undo;
	}

	public int activeColor() {
		return activeColor;
	}

	public Piece piece(int field) {
		assert valid(field);
		return board[field];
	}

	public void clear(int field) {
		assert board[field] != null;
		assert board[field].type() != Border : format("cannot clear border: %s", field);
		board[field] = null;
	}

	public void place(Piece piece) {
		assert piece != null;
		assert PieceType.black(piece.type()) || PieceType.white(piece.type());
		assert 
			PieceType.black(piece.type()) && blackPieces.contains(piece) || 
			PieceType.white(piece.type()) && whitePieces.contains(piece);
		
		board[piece.field()] = piece;
	}

	public void enPassant(int field) {
		assert field != removed;
		assert board[field] == null;
		assert Field.valid(field);
		assert 
			activeColor == white && rank(field) == rank(a3) && board[Field.up(field)] != null && board[Field.up(field)].type() == WhitePawn||
			activeColor == black && rank(field) == rank(a6) && board[Field.down(field)] != null && board[Field.down(field)].type() == BlackPawn;
		
		enPassant = field;
	}

	public int enPassant() {
		return enPassant;
	}
	
	public void incHalfMoveClock() {
		halfMoveClock++;
	}

	public void decHalfMoveClock() {
		assert halfMoveClock > 0;
		halfMoveClock--;
	}

	public void pushHalfMoveClock() {
		halfMoveClockStack[halfMoveClockStackIndex++] = halfMoveClock;
		halfMoveClock = 0;
	}

	public void popHalfMoveClock() {
		halfMoveClock = halfMoveClockStack[--halfMoveClockStackIndex];
	}
	
	public void resetFullMoveNumber() {
		fullMoveNumber = 1;
	}
	
	public void activeColorWhite() {
		activeColor = white;
	}
	
	public void activeColorBlack() {
		activeColor = black;
	}

	public int halfMoveClock() {
		return halfMoveClock;
	}

	public int fullMoveNumber() {
		return fullMoveNumber;
	}

	public void fullMoveNumber(int fullMoveNumber) {
		assert fullMoveNumber > 0;
		this.fullMoveNumber = fullMoveNumber;
	}

	public void incFullMoveNumber() {
		fullMoveNumber++;
	}

	public void decFullMoveNumber() {
		assert fullMoveNumber > 1;
		fullMoveNumber--;
	}
	
	public boolean containsBlackPiece(Piece piece) {
		assert piece != null;
		assert PieceType.black(piece.type());
		return blackPieces.contains(piece);
	}

	public boolean containsWhitePiece(Piece piece) {
		assert piece != null;
		assert PieceType.white(piece.type());
		return whitePieces.contains(piece);
	}
	
	public Moves moves() {
		switch(activeColor()) {
		case white: return whiteMoves();
		case black: return blackMoves();
		default: return throwHE("unknown color %s", activeColor());
		}
	}

	public Moves whiteMoves() {
		Moves moves = new Moves();
		whiteMoves.generate(whitePieces, castlingRightHistory.castlingRight().white(), enPassant, moves);
		return moves;
	}
	
	public Moves whiteCaptureMoves() {
		Moves moves = new Moves();
		whiteCaptureMoves.generate(whitePieces, enPassant, moves);
		return moves;
	}
	
	public Moves blackMoves() {
		Moves moves = new Moves();
		blackMoves.generate(blackPieces, castlingRightHistory.castlingRight().black(), enPassant, moves);
		return moves;
	}

	public Moves blackCaptureMoves() {
		Moves moves = new Moves();
		blackCaptureMoves.generate(blackPieces, enPassant, moves);
		return moves;
	}

	public boolean assertVerify() {
		assert doVerify();
		return true;
	}
	
	private boolean doVerify() {
		assert Board.assertVerify(board);
		assert halfMoveClock >= 0;
		assert fullMoveNumber > 0;
		assert pieceValue == calculatePieceValue();
		assert whitePositionValue == calculateWhitePositionValue() : format("%s != %s", whitePositionValue, calculateWhitePositionValue());
		assert blackPositionValue == calculateBlackPositionValue() : format("%s != %s", blackPositionValue, calculateBlackPositionValue());
		
		int size = whitePieces.index();
		for(int i = 0; i < size; i++) {
			Piece piece = whitePieces.piece(i);
			assert PieceType.white(piece.type());
			assert 
				(!piece.captured() && board[piece.field()] == piece) ||
				(piece.captured() && board[piece.field()] != piece) 				
					: piece.toString() + " != " + board[piece.field()];
		}

		size = blackPieces.index();
		for(int i = 0; i < size; i++) {
			Piece piece = blackPieces.piece(i);
			assert PieceType.black(piece.type());
			assert 
				(!piece.captured() && board[piece.field()] == piece) ||
				(piece.captured() && board[piece.field()] != piece) 				
					: piece.toString() + " != " + board[piece.field()];
		}
		
		int fy = Field.a1;
		for(int y = 0; y < 8; y++) {
			int fx = fy;
			for(int x = 0; x < 8; x++) {
				Piece piece = board[fx];
				assert piece == null || piece.type() != Border;
				if (piece != null) {
					if (PieceType.black(piece.type())) 
						assert blackPieces.contains(piece);
					else if (PieceType.white(piece.type()))
						assert whitePieces.contains(piece);
					else
						assert false;
				}
				fx = right(fx);
			}
			fy = up(fy);
			
		}
		
		Piece king = piece(e1);
		boolean queenside = castlingRight().white().queenside();
		boolean kingside = castlingRight().white().kingside();
		
		if (king == null || king.type() != WhiteKing) {
			assert !queenside;
			assert !kingside;
		}
		else {
			Piece rook = piece(a1);
			if (rook == null || rook.type() != PieceType.WhiteRook) 
				assert !queenside;
		
			rook = piece(h1);
			if (rook == null || rook.type() != PieceType.WhiteRook)
				assert !kingside;
		}

		king = piece(e8);
		queenside = castlingRight().black().queenside();
		kingside = castlingRight().black().kingside();
		
		if (king == null || king.type() != BlackKing) {
			assert !queenside;
			assert !kingside;
		}
		else {
			Piece rook = piece(a8);
			if (rook == null || rook.type() != BlackRook)
				assert !queenside;

			rook = piece(h8);
			if (rook == null || rook.type() != BlackRook)
				assert !kingside;
		}
		
		if (castlingRight().white().queenside())
			assert piece(e1).type() == PieceType.WhiteKing && piece(a1).type() == PieceType.WhiteRook;
	
		if (castlingRight().white().kingside())
			assert piece(e1).type() == PieceType.WhiteKing && piece(h1).type() == PieceType.WhiteRook;
		
		return true;
	}

 	public void whitePositionValue(int type, int from, int to) {
 		assert PieceType.white(type);
 		assert Field.valid(from);
 		assert Field.valid(to);
 		
		whitePositionValue -= whitePiecePositionValue.value(type, from);
		whitePositionValue += whitePiecePositionValue.value(type, to);
 	}

 	public void blackPositionValue(int type, int from, int to) {
 		assert PieceType.black(type);
 		assert Field.valid(from);
 		assert Field.valid(to);
 		
 		blackPositionValue -= blackPiecePositionValue.value(type, from);
 		blackPositionValue += blackPiecePositionValue.value(type, to);
 	}
 	
  	public void addWhite(Piece piece) {
		assert piece != null;
		assert PieceType.white(piece.type());
		assert whitePieces.contains(piece);

		pieceValue += PieceValue.pieceValue(piece.type());
		whitePositionValue += whitePiecePositionValue.value(piece);
		
		assert pieceValue == calculatePieceValue();
	}
	
	public void removeWhite(Piece piece) {
		assert piece != null;
		assert PieceType.white(piece.type());
		assert piece.type() != WhiteKing;
		assert whitePieces.contains(piece);
		
		pieceValue -= PieceValue.pieceValue(piece.type());
		whitePositionValue -= whitePiecePositionValue.value(piece);
		
		assert pieceValue == calculatePieceValue();
	}
	
	public void addBlack(Piece piece) {
		assert piece != null;
		assert PieceType.black(piece.type());
		assert blackPieces.contains(piece);

		pieceValue -= PieceValue.pieceValue(piece.type());
		blackPositionValue += blackPiecePositionValue.value(piece);

		assert pieceValue == calculatePieceValue();
	}
	
	public void removeBlack(Piece piece) {
		assert piece != null;
		assert PieceType.black(piece.type());
		assert piece.type() != BlackKing;
		assert blackPieces.contains(piece);
		
		pieceValue += PieceValue.pieceValue(piece.type());
		blackPositionValue -= blackPiecePositionValue.value(piece);

		assert pieceValue == calculatePieceValue();
	}

	public int evaluate() {
		assert pieceValue == calculatePieceValue();
		
		return pieceValue + calculatePiecePositionValue();
	}

	private int calculatePieceValue() {
		int whiteValue = pieceValues(whitePieces);
		int blackValue = pieceValues(blackPieces);
		return whiteValue - blackValue;
	}
	
	private int calculatePiecePositionValue() {
		assert whitePositionValue == calculateWhitePositionValue();
		assert blackPositionValue == calculateBlackPositionValue();
		
		return whitePositionValue - blackPositionValue;
	}

	private int calculateWhitePositionValue() {
		int value = 0;
		int size = whitePieces.index();
		for(int i = 0; i < size; i++)
			if (!whitePieces.piece(i).captured())
				value += whitePiecePositionValue.value(whitePieces.piece(i));
		return value;
	}

	private int calculateBlackPositionValue() {
		int value = 0;
		int size = blackPieces.index();
		for(int i = 0; i < size; i++) 
			if (!blackPieces.piece(i).captured())
				value += blackPiecePositionValue.value(blackPieces.piece(i));
		
		return value;
	}
	
	private static int pieceValues(PieceList pieces) {
		int value = 0;
		int size = pieces.index();
		for(int i = 0; i < size; i++) 
			if (!pieces.piece(i).captured())
			value += PieceValue.pieceValue(pieces.piece(i).type());
		
		return value;
	}

	public Piece[] pieces() {
		return board;
	}

	public PositionCastlingRight castlingRight() {
		return castlingRightHistory.castlingRight();
	}

	public void pushCastlingRight() {
		castlingRightHistory.push();
	}

	public void popCastlingRight() {
		castlingRightHistory.pop();
	}

}
