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
	
	public WhiteCaptureMoves(Board board) {
		assert board != null;
		
		this.kingMoves = new WhiteKingCaptureMoves(board);
		this.rookMoves = new WhiteRookCaptureMoves(board);
		this.knightMoves = new WhiteKnightCaptureMoves(board);
		this.bishopMoves = new WhiteBishopCaptureMoves(board);
		this.queenMoves = new WhiteQueenCaptureMoves(board);
		this.pawnMoves = new WhitePawnCaptureMoves(board);
	}
	
	public void generate(PieceList pieces, Field epField, Moves moves) {
		assert pieces != null;
		assert moves != null;
		assert epField != null;
		assert pieces.size() > 0;
		assert epField == removed || epField.rank() == 5;
		assert !moves.kingCaptured();
		
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
