/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves.white;

import static com.haymel.chess.engine.board.Field.leftDown;
import static com.haymel.chess.engine.board.Field.rank;
import static com.haymel.chess.engine.board.Field.removed;
import static com.haymel.chess.engine.board.Field.rightDown;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
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

	private final Piece[] pieces;
	private final WhiteKingMoves kingMoves;
	private final WhiteRookMoves rookMoves;
	private final WhiteKnightMoves knightMoves;
	private final WhiteBishopMoves bishopMoves;
	private final WhiteQueenMoves queenMoves;
	private final WhitePawnMoves pawnMoves;
	
	public WhiteMoves(Piece[] pieces) {
		assert pieces != null;
		
		this.pieces = pieces;
		this.kingMoves = new WhiteKingMoves(pieces);
		this.rookMoves = new WhiteRookMoves(pieces);
		this.knightMoves = new WhiteKnightMoves(pieces);
		this.bishopMoves = new WhiteBishopMoves(pieces);
		this.queenMoves = new WhiteQueenMoves(pieces);
		this.pawnMoves = new WhitePawnMoves(pieces);
	}
	
	public void generate(PieceList pieces, CastlingRight castling, int epField, Moves moves) {
		assert pieces != null;
		assert pieces.verify();
		assert epField == removed || rank(epField) == 5;
		
		int size = pieces.index();
		for(int i = 0; i < size; i++)
			generate(pieces.piece(i), castling, epField, moves);
		
		if (epField != removed)
			enpassant(epField, moves);
	}

	private void enpassant(int epField, Moves moves) {
		assert epField != removed;
		
		int leftDown = leftDown(epField);
		if (whitePawn(pieces[leftDown]))
			moves.add(leftDown, epField, enpassant);
			
		int rightDown = rightDown(epField);
		if (whitePawn(pieces[rightDown]))
			moves.add(rightDown, epField, enpassant);
	}

	private static boolean whitePawn(Piece piece) {
		return piece != null && piece.type() == WhitePawn;
	}
	
	private void generate(Piece piece, CastlingRight castling, int epField, Moves moves) {
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
			kingMoves.generate(piece, castling, moves);
			break;
		default:
			assert false;
			break;
		}
	}
	
}
