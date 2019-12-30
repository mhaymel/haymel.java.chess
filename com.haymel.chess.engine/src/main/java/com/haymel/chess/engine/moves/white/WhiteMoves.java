/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import com.haymel.chess.engine.board.Board;
import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;

public class WhiteMoves {		//TODO unit test

	private final Board board;
	private final Moves moves;
	
	private WhiteKingMoves kingMoves;
	private WhiteRookMoves rookMoves;
	private WhiteKnightMoves knightMoves;
	private WhiteBishopMoves bishopMoves;
	
	public WhiteMoves(Board board, Moves moves) {
		assert board != null;
		assert moves != null;
		
		this.board = board;
		this.moves = moves;
		this.kingMoves = new WhiteKingMoves(board, moves);
		this.rookMoves = new WhiteRookMoves(board, moves);
		this.rookMoves = new WhiteRookMoves(board, moves);
		this.knightMoves = new WhiteKnightMoves(board, moves);
		this.bishopMoves = new WhiteBishopMoves(board, moves);
	}
	
	public void generate(PieceList pieces) {
		int size = pieces.size();
		
		assert size > 1;
		
		for(int i = 0; i < size; i++)
			generate(pieces.piece(i));
	}

	private void generate(Piece piece) {
		assert piece != null;
		assert piece.white();
		
		switch(piece.type()) {
		case WhitePawn:
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
			break;
		case WhiteKing:
			kingMoves.generate(piece);
			break;
		default:
			assert false;
		}
	}
	
}
