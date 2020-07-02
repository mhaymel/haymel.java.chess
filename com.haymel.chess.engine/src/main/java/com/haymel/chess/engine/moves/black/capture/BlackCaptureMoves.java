/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.leftUp;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightUp;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class BlackCaptureMoves {		//TODO unit test

	private final Piece[] pieces;
	private final BlackKingCaptureMoves kingMoves;
	private final BlackRookCaptureMoves rookMoves;
	private final BlackKnightCaptureMoves knightMoves;
	private final BlackBishopCaptureMoves bishopMoves;
	private final BlackQueenCaptureMoves queenMoves;
	private final BlackPawnCaptureMoves pawnMoves;
	
	public BlackCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.pieces = pieces;
		this.kingMoves = new BlackKingCaptureMoves(pieces);
		this.rookMoves = new BlackRookCaptureMoves(pieces);
		this.knightMoves = new BlackKnightCaptureMoves(pieces);
		this.bishopMoves = new BlackBishopCaptureMoves(pieces);
		this.queenMoves = new BlackQueenCaptureMoves(pieces);
		this.pawnMoves = new BlackPawnCaptureMoves(pieces);
	}
	
	public boolean generate(PieceList pieces, int epField, Moves moves) {
		assert pieces != null;
		assert pieces.verify();
		assert Field.valid(epField);
		assert moves != null;
		assert pieces.index() > 0;
		assert epField == removed || Field.rank(epField) == 2 : String.format("wrong enpassant field: %s", fieldAsString(epField));
		
		int size = pieces.index();
		for(int i = 0; i < size; i++)
			if (!generate(pieces.piece(i), epField, moves))
				return false;
		
		if (epField != removed)
			enpassant(epField, moves);

		return true;
	}

	private void enpassant(int epField, Moves moves) {
		assert epField != removed;
		
		int leftUp = leftUp(epField);
		if (blackPawn(pieces[leftUp]))
			moves.add(leftUp, epField, enpassant);
			
		int rightUp = rightUp(epField);
		if (blackPawn(pieces[rightUp]))
			moves.add(rightUp, epField, enpassant);
	}

	private static boolean blackPawn(Piece piece) {
		return piece != null && piece.type() == BlackPawn;
	}

	private boolean generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert PieceType.black(piece.type());

		if (piece.captured())
			return true;
		
		switch(piece.type()) {
		case BlackPawn: 	return pawnMoves.generate(piece, epField, moves);
		case BlackRook:		return rookMoves.generate(piece, moves);
		case BlackKnight:	return knightMoves.generate(piece, moves);
		case BlackBishop:   return bishopMoves.generate(piece, moves);
		case BlackQueen:	return queenMoves.generate(piece, moves);
		case BlackKing:		return kingMoves.generate(piece, moves);
		default:
			assert false;
			return false;
		}
	}
	
}
