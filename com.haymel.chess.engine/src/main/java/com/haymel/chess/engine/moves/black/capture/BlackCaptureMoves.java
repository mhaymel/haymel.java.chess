/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black.capture;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
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
	
	public BlackCaptureMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.kingMoves = new BlackKingCaptureMoves(board, moves);
		this.rookMoves = new BlackRookCaptureMoves(board, moves);
		this.knightMoves = new BlackKnightCaptureMoves(board, moves);
		this.bishopMoves = new BlackBishopCaptureMoves(board, moves);
		this.queenMoves = new BlackQueenCaptureMoves(board, moves);
		this.pawnMoves = new BlackPawnCaptureMoves(board, moves);
	}
	
	public void generate(PieceList pieces, Field epField) {
		assert pieces != null;
		assert epField != null;
		assert pieces.size() > 0;
		assert epField == removed || epField.rank() == 2 : String.format("wrong enpassant field: %s", epField);
		
		int size = pieces.size();
		for(int i = 0; i < size; i++)
			generate(pieces.piece(i), epField);
	}

	private void generate(Piece piece, Field epField) {
		assert piece != null;
		assert piece.black();
		
		switch(piece.type()) {
		case BlackPawn:
			pawnMoves.generate(piece, epField);
			break;
		case BlackRook:
			rookMoves.generate(piece);
			break;
		case BlackKnight:
			knightMoves.generate(piece);
			break;
		case BlackBishop:
			bishopMoves.generate(piece);
			break;
		case BlackQueen:
			queenMoves.generate(piece);
			break;
		case BlackKing:
			kingMoves.generate(piece);
			break;
		default:
			assert false;
		}
	}
	
}
