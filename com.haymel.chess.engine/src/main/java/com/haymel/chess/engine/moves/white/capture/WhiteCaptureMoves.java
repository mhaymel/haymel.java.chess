/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	13.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white.capture;

import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhiteCaptureMoves {		//TODO unit test

	private final WhiteKingCaptureMoves kingMoves;
	private final WhiteRookCaptureMoves rookMoves;
	private final WhiteKnightCaptureMoves knightMoves;
	private final WhiteBishopCaptureMoves bishopMoves;
	private final WhiteQueenCaptureMoves queenMoves;
	private final WhitePawnCaptureMoves pawnMoves;
	
	public WhiteCaptureMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.kingMoves = new WhiteKingCaptureMoves(pieces);
		this.rookMoves = new WhiteRookCaptureMoves(pieces);
		this.knightMoves = new WhiteKnightCaptureMoves(pieces);
		this.bishopMoves = new WhiteBishopCaptureMoves(pieces);
		this.queenMoves = new WhiteQueenCaptureMoves(pieces);
		this.pawnMoves = new WhitePawnCaptureMoves(pieces);
	}
	
	public void generate(PieceList pieces, int epField, Moves moves) {
		assert pieces != null;
		assert moves != null;
		assert pieces.verify();
		assert epField == removed || rank(epField) == 5;
		assert !moves.kingCaptured();
		
		int size = pieces.index();
		for(int i = 0; i < size && !moves.kingCaptured(); i++)
			generate(pieces.piece(i), epField, moves);
	}

	private void generate(Piece piece, int epField, Moves moves) {
		assert piece != null;
		assert PieceType.white(piece.type());
		
		if (piece.captured())
			return;
		
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
