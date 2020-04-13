/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteMoves {		//TODO unit test

	private final WhiteKingMoves kingMoves;
	private final WhiteRookMoves rookMoves;
	private final WhiteKnightMoves knightMoves;
	private final WhiteBishopMoves bishopMoves;
	private final WhiteQueenMoves queenMoves;
	private final WhitePawnMoves pawnMoves;
	
	public WhiteMoves(Board board) {
		assert board != null;
		
		this.kingMoves = new WhiteKingMoves(board.pieces);
		this.rookMoves = new WhiteRookMoves(board);
		this.knightMoves = new WhiteKnightMoves(board);
		this.bishopMoves = new WhiteBishopMoves(board);
		this.queenMoves = new WhiteQueenMoves(board);
		this.pawnMoves = new WhitePawnMoves(board);
	}
	
	public void generate(PieceList pieces, Field epField, Moves moves) {
		assert pieces != null;
		assert epField != null;
		assert pieces.size() > 0;
		assert epField == removed || epField.rank() == 5;
		
		int size = pieces.size();
		for(int i = 0; i < size && !moves.kingCaptured(); i++)
			generate(pieces.piece(i), epField, moves);
	}

	private void generate(Piece piece, Field epField, Moves moves) {
		assert piece != null;
		assert piece.white();
		
		switch(piece.type()) {
		case WhitePawn:
			pawnMoves.generate(piece, epField, moves);
			break;
		case WhiteRook:
			rookMoves.generate(piece, moves);
			break;
		case WhiteKnight:
			knightMoves.generate(piece, moves);
			break;
		case WhiteBishop:
			bishopMoves.generate(piece, moves);
			break;
		case WhiteQueen:
			queenMoves.generate(piece, moves);
			break;
		case WhiteKing:
			kingMoves.generate(piece, moves);
			break;
		default:
			assert false;
		}
	}
	
}
