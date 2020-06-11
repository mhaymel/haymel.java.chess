/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.PieceList;
import com.haymel.chess.engine.castling.CastlingRight;
import com.haymel.chess.engine.moves.Moves;
import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

public final class WhiteMoves {		//TODO unit test

	private final WhiteKingMoves kingMoves;
	private final WhiteRookMoves rookMoves;
	private final WhiteKnightMoves knightMoves;
	private final WhiteBishopMoves bishopMoves;
	private final WhiteQueenMoves queenMoves;
	private final WhitePawnMoves pawnMoves;
	
	public WhiteMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.kingMoves = new WhiteKingMoves(pieces);
		this.rookMoves = new WhiteRookMoves(pieces);
		this.knightMoves = new WhiteKnightMoves(pieces);
		this.bishopMoves = new WhiteBishopMoves(pieces);
		this.queenMoves = new WhiteQueenMoves(pieces);
		this.pawnMoves = new WhitePawnMoves(pieces);
	}
	
	public boolean generate(PieceList pieces, CastlingRight castling, int epField, Moves moves) {
		assert pieces != null;
		assert pieces.verify();
		assert epField == removed || rank(epField) == 5;
		
		int size = pieces.index();
		for(int i = 0; i < size; i++)
			if (!generate(pieces.piece(i), castling, epField, moves))
				return false;
		
		return true;
	}

	private boolean generate(Piece piece, CastlingRight castling, int epField, Moves moves) {
		assert piece != null;
		assert PieceType.white(piece.type());
		
		if (piece.captured())
			return true;
		
		switch(piece.type()) {
		case WhitePawn:		return pawnMoves.generate(piece, epField, moves);
		case WhiteRook:		return rookMoves.generate(piece, moves);
		case WhiteKnight:	return knightMoves.generate(piece, moves);
		case WhiteBishop:	return bishopMoves.generate(piece, moves);
		case WhiteQueen:	return queenMoves.generate(piece, moves);
		case WhiteKing:		return kingMoves.generate(piece, castling, moves);
		default:
			assert false;
			return false;
		}
	}
	
}
