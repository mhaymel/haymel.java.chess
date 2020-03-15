/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	02.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.black;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class BlackMoves {		//TODO unit test

	private final BlackKingMoves kingMoves;
	private final BlackRookMoves rookMoves;
	private final BlackKnightMoves knightMoves;
	private final BlackBishopMoves bishopMoves;
	private final BlackQueenMoves queenMoves;
	private final BlackPawnMoves pawnMoves;
	
	public BlackMoves(Board board) {
		assert board != null;
		
		this.kingMoves = new BlackKingMoves(board);
		this.rookMoves = new BlackRookMoves(board);
		this.knightMoves = new BlackKnightMoves(board);
		this.bishopMoves = new BlackBishopMoves(board);
		this.queenMoves = new BlackQueenMoves(board);
		this.pawnMoves = new BlackPawnMoves(board);
	}
	
	public void generate(PieceList pieces, Field epField, Moves moves) {
		assert pieces != null;
		assert epField != null;
		assert moves != null;
		assert pieces.size() > 0;
		assert epField == removed || epField.rank() == 2 : String.format("wrong enpassant field: %s", epField);
		
		int size = pieces.size();
		for(int i = 0; i < size && !moves.kingCaptured(); i++)
			generate(pieces.piece(i), epField, moves);
	}

	private void generate(Piece piece, Field epField, Moves moves) {
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
