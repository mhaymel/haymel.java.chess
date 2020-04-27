/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	27.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import static com.haymel.chess.engine.board.Board.newBoard;
import static com.haymel.chess.engine.board.Field.a3;
import static com.haymel.chess.engine.board.Field.a6;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.right;
import static com.haymel.chess.engine.board.Field.up;
import static com.haymel.chess.engine.board.Field.valid;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.MoveType.capturePromotion;
import static com.haymel.chess.engine.moves.MoveType.promotion;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
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

public final class Game {	//TODO unit test and refactor

	private final List<Undo> undos = new ArrayList<>();
	private final PieceList whitePieces = new PieceList();
	private final PieceList blackPieces = new PieceList();
	private Piece[] board;
	private ActiveColor activeColor;
	private int enPassant;
	private int halfMoveClock;
	private int fullMoveNumber;
	private final WhiteMoves whiteMoves;
	private final WhiteCaptureMoves whiteCaptureMoves;
	private final BlackMoves blackMoves;
	private final BlackCaptureMoves blackCaptureMoves;
	private int pieceValue;
	private PiecePositionValue whitePiecePositionValue;
	private PiecePositionValue blackPiecePositionValue;
	
//	public Game() {
//		this(PiecePositionValue.noopPiecePositionValue, PiecePositionValue.noopPiecePositionValue);
//	}
	
	public Game() {
		this(new WhitePiecesPositionValue(), new BlackPiecesPositionValue());
	}

	public Game(PiecePositionValue whitePiecePositionValue, PiecePositionValue blackPiecePositionValue) {
		this.board = newBoard();
		this.whiteMoves = new WhiteMoves(board);
		this.whiteCaptureMoves = new WhiteCaptureMoves(board);
		this.blackMoves = new BlackMoves(board);
		this.blackCaptureMoves = new BlackCaptureMoves(board);
		this.whitePiecePositionValue = nonNull(whitePiecePositionValue, "whitePiecePositionValue");
		this.blackPiecePositionValue = nonNull(blackPiecePositionValue, "blackPiecePositionValue");
		reset();
		assertVerify();
	}

	public void reset() {
		Board.reset(board);
		activeColor = white;
		enPassant = removed;
		halfMoveClock = 0;
		fullMoveNumber = 1;
		undos.clear();
		whitePieces.clear();
		blackPieces.clear();
		pieceValue = 0;
		
		assertVerify();
	}
	
	public void push(Move move) {
		push(move, true);
	}

	public void push(Move move, boolean moved) {
		push(new Undo(move, moved, activeColor, enPassant, halfMoveClock, fullMoveNumber));
		resetEnPassant();
	}
	
	public Undo pop() {
		Undo undo = undos.remove(undos.size() - 1);
		
		activeColor = undo.activeColor();
		enPassant = undo.enPassant();
		halfMoveClock = undo.halfMoveClock();
		fullMoveNumber = undo.fullMoveNumber();
		
		return undo;
	}

	public ActiveColor activeColor() {
		return activeColor;
	}

	public Piece piece(int field) {
		assert valid(field);
		return board[field];
	}

	public void clear(int field) {
		assert board[field] != null;
		assert !board[field].border() : format("cannot clear border: %s", field);
		board[field] = null;
	}

	public void place(Piece piece) {
		assert piece != null;
		assert piece.black() || piece.white();
		assert 
			piece.black() && blackPieces.contains(piece) || 
			piece.white() && whitePieces.contains(piece);
		
		board[piece.field()] = piece;
	}

	private void push(Undo undo) {
		undos.add(undo);
	}

	public void resetEnPassant() {
		enPassant(removed);
	}

	public void enPassant(int field) {
		assert Field.valid(field);
		assert field == removed || board[field] == null;
		assert 
			field == removed ||
			activeColor == white && rank(field) == rank(a3) && board[Field.up(field)].whitePawn()||
			activeColor == black && rank(field) == rank(a6) && board[Field.down(field)].blackPawn();
		
		enPassant = field;
	}

	public int enPassant() {
		return enPassant;
	}
	
	public void incHalfMoveClock() {
		halfMoveClock++;
	}

	public void resetHalfMoveClock() {
		halfMoveClock(0);
	}
	
	public void halfMoveClock(int value) {
		assert value >= 0;
		
		halfMoveClock = value;
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

	public boolean containsBlackPiece(Piece piece) {
		assert piece != null;
		assert piece.black();
		return blackPieces.contains(piece);
	}

	public boolean containsWhitePiece(Piece piece) {
		assert piece != null;
		assert piece.white();
		return whitePieces.contains(piece);
	}
	
	public Moves whiteMoves() {
		Moves moves = new Moves();
		whiteMoves.generate(whitePieces, enPassant, moves);
		return moves;
	}
	
	public Moves whiteCaptureMoves() {
		Moves moves = new Moves();
		whiteCaptureMoves.generate(whitePieces, enPassant, moves);
		return moves;
	}
	
	public Moves blackMoves() {
		Moves moves = new Moves();
		blackMoves.generate(blackPieces, enPassant, moves);
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
		
		int size = whitePieces.size();
		for(int i = 0; i < size; i++) {
			Piece piece = whitePieces.piece(i);
			assert piece.white();
			assert board[piece.field()] == piece : piece.toString() + " != " + board[piece.field()];
		}

		size = blackPieces.size();
		for(int i = 0; i < size; i++) {
			Piece piece = blackPieces.piece(i);
			assert piece.black();
			assert board[piece.field()] == piece;
		}
		
		int fy = Field.a1;
		for(int y = 0; y < 8; y++) {
			int fx = fy;
			for(int x = 0; x < 8; x++) {
				Piece p = board[fx];
				assert p == null || !p.border();
				if (p != null) {
					if (p.black()) 
						assert blackPieces.contains(p);
					else if (p.white())
						assert whitePieces.contains(p);
					else
						assert false;
				}
				fx = right(fx);
			}
			fy = up(fy);
			
		}
		return true;
	}

	public void addWhite(Piece piece) {
		assert piece != null;
		assert piece.white();
		assert !whitePieces.contains(piece);
		assert pieceValue == calculatePieceValue();

		whitePieces.add(piece);
		pieceValue += PieceValue.pieceValue(piece.type());
				
		assert pieceValue == calculatePieceValue();
	}
	
	public void removeWhite(Piece piece) {
		assert piece != null;
		assert piece.white();
		assert !piece.whiteKing();
		assert whitePieces.contains(piece);
		assert pieceValue == calculatePieceValue() : format("%s != %s, %s", pieceValue, calculatePieceValue(), piece);
		
		whitePieces.remove(piece);
		pieceValue -= PieceValue.pieceValue(piece.type());

		assert pieceValue == calculatePieceValue();
	}
	
	public void addBlack(Piece piece) {
		assert piece != null;
		assert piece.black();
		assert !blackPieces.contains(piece);
		assert pieceValue == calculatePieceValue();

		blackPieces.add(piece);
		pieceValue -= PieceValue.pieceValue(piece.type());

		assert pieceValue == calculatePieceValue();
	}
	
	public void removeBlack(Piece piece) {
		assert piece != null;
		assert piece.black();
		assert !piece.blackKing();
		assert blackPieces.contains(piece);
		assert pieceValue == calculatePieceValue();
		
		blackPieces.remove(piece);
		pieceValue += PieceValue.pieceValue(piece.type());

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
		int value = 0;
		int size = whitePieces.size();
		for(int i = 0; i < size; i++) 
			value += whitePiecePositionValue.value(whitePieces.piece(i));

		size = blackPieces.size();
		for(int i = 0; i < size; i++) 
			value -= blackPiecePositionValue.value(blackPieces.piece(i));
		
		return value;
	}

	private static int pieceValues(PieceList pieces) {
		int value = 0;
		int size = pieces.size();
		for(int i = 0; i < size; i++) 
			value += PieceValue.pieceValue(pieces.piece(i).type());
		
		return value;
	}

	public Piece[] pieces() {
		return board;
	}

	public void blackPromotion(Move move) {
		assert move != null;
		assert move.type() == capturePromotion || move.type() == promotion;
		
		pieceValue = pieceValue + PieceValue.pieceValue(BlackPawn) - PieceValue.pieceValue(move.pieceType());
		
		assert pieceValue == calculatePieceValue() : String.format("%s != %s", pieceValue, calculatePieceValue());
	}

	public void blackUndoPromotion(Move move) {
		assert move != null;
		assert move.type() == capturePromotion || move.type() == promotion;

		pieceValue = pieceValue - PieceValue.pieceValue(BlackPawn) + PieceValue.pieceValue(move.pieceType());

		assert pieceValue == calculatePieceValue();
	}

	public void whitePromotion(Move move) {
		assert move != null;
		assert move.type() == capturePromotion || move.type() == promotion;

		pieceValue = pieceValue - PieceValue.pieceValue(WhitePawn) + PieceValue.pieceValue(move.pieceType());
		
		assert pieceValue == calculatePieceValue();
	}

	public void whiteUndoPromotion(Move move) {
		assert move != null;
		assert move.type() == capturePromotion || move.type() == promotion;

		pieceValue = pieceValue + PieceValue.pieceValue(BlackPawn) - PieceValue.pieceValue(move.pieceType());
		
		assert pieceValue == calculatePieceValue();
	}
	
}
