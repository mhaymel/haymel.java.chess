/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.board.Field.removed;
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

public final class BlackCaptureMoves {		//TODO unit test

	private final BlackKingCaptureMoves kingMoves;
	private final BlackRookCaptureMoves rookMoves;
	private final BlackKnightCaptureMoves knightMoves;
	private final BlackBishopCaptureMoves bishopMoves;
	private final BlackQueenCaptureMoves queenMoves;
	private final BlackPawnCaptureMoves pawnMoves;
	
	public BlackCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.kingMoves = new BlackKingCaptureMoves(pieces);
		this.rookMoves = new BlackRookCaptureMoves(pieces);
		this.knightMoves = new BlackKnightCaptureMoves(pieces);
		this.bishopMoves = new BlackBishopCaptureMoves(pieces);
		this.queenMoves = new BlackQueenCaptureMoves(pieces);
		this.pawnMoves = new BlackPawnCaptureMoves(pieces);
	}
	
	public void generate(PieceList pieces, int epField, Moves moves) {
		assert pieces != null;
		assert Field.valid(epField);
		assert moves != null;
		assert pieces.size() > 0;
		assert epField == removed || Field.rank(epField) == 2 : String.format("wrong enpassant field: %s", fieldAsString(epField));
		assert !moves.kingCaptured();
		
		int size = pieces.size();
		for(int i = 0; i < size && !moves.kingCaptured(); i++)
			generate(pieces.piece(i), epField, moves);
	}

	private void generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert piece.black();
		
		switch(piece.type()) {
		case BlackPawn:
			pawnMoves.generate(piece, epField, moves);
			break;
		case BlackRook:
			rookMoves.generate(piece, moves);
			break;
		case BlackKnight:
			knightMoves.generate(piece, moves);
			break;
		case BlackBishop:
			bishopMoves.generate(piece, moves);
			break;
		case BlackQueen:
			queenMoves.generate(piece, moves);
			break;
		case BlackKing:
			kingMoves.generate(piece, moves);
			break;
		default:
			assert false;
		}
	}
	
}
