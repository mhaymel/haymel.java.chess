/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.removed;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public final class WhiteCaptureMoves {		//TODO unit test

	private final WhiteKingCaptureMoves kingMoves;
	private final WhiteRookCaptureMoves rookMoves;
	private final WhiteKnightCaptureMoves knightMoves;
	private final WhiteBishopCaptureMoves bishopMoves;
	private final WhiteQueenCaptureMoves queenMoves;
	private final WhitePawnCaptureMoves pawnMoves;
	
	public WhiteCaptureMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.kingMoves = new WhiteKingCaptureMoves(board, moves);
		this.rookMoves = new WhiteRookCaptureMoves(board, moves);
		this.knightMoves = new WhiteKnightCaptureMoves(board, moves);
		this.bishopMoves = new WhiteBishopCaptureMoves(board, moves);
		this.queenMoves = new WhiteQueenCaptureMoves(board, moves);
		this.pawnMoves = new WhitePawnCaptureMoves(board, moves);
	}
	
	public void generate(PieceList pieces, Field epField) {
		assert pieces != null;
		assert epField != null;
		assert pieces.size() > 0;
		assert epField == removed || epField.rank() == 5;
		
		int size = pieces.size();
		for(int i = 0; i < size; i++)
			generate(pieces.piece(i), epField);
	}

	private void generate(Piece piece, Field epField) {
		assert piece != null;
		assert piece.white();
		
		switch(piece.type()) {
		case WhitePawn:
			pawnMoves.generate(piece, epField);
			break;
		case WhiteRook:
			rookMoves.generate(piece);
			break;
		case WhiteKnight:
			knightMoves.generate(piece);
			break;
		case WhiteBishop:
			bishopMoves.generate(piece);
			break;
		case WhiteQueen:
			queenMoves.generate(piece);
			break;
		case WhiteKing:
			kingMoves.generate(piece);
			break;
		default:
			assert false;
		}
	}
	
}
