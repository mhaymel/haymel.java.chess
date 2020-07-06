/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	04.07.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.board.Field.leftDownDown;
import static com.haymel.chess.engine.board.Field.leftLeftDown;
import static com.haymel.chess.engine.board.Field.leftLeftUp;
import static com.haymel.chess.engine.board.Field.leftUpUp;
import static com.haymel.chess.engine.board.Field.rightDownDown;
import static com.haymel.chess.engine.board.Field.rightRightDown;
import static com.haymel.chess.engine.board.Field.rightRightUp;
import static com.haymel.chess.engine.board.Field.rightUpUp;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public class BlackInCheck {

	public static boolean blackIsInCheck(int kingPosition, Piece[] pieces) {
		assert Field.valid(kingPosition);
		assert pieces[kingPosition].type() == BlackKing;
	
		
		return
			up(kingPosition, pieces) ||
			rightUp(kingPosition, pieces) ||
			leftUp(kingPosition, pieces) ||
			left(kingPosition, pieces) ||
			right(kingPosition, pieces) ||
			down(kingPosition, pieces) ||
			leftDown(kingPosition, pieces) ||
			rightDown(kingPosition, pieces) ||
			knight(kingPosition, pieces) ||
			king(kingPosition, pieces) ||
			pawn(kingPosition, pieces);
	}

	private static boolean king(int kingPosition, Piece[] pieces) {
		return
			isWhiteKing(pieces[Field.left(kingPosition)]) ||
			isWhiteKing(pieces[Field.right(kingPosition)]) ||
			isWhiteKing(pieces[Field.up(kingPosition)]) ||
			isWhiteKing(pieces[Field.down(kingPosition)]) ||
			isWhiteKing(pieces[Field.leftUp(kingPosition)]) ||
			isWhiteKing(pieces[Field.leftDown(kingPosition)]) ||
			isWhiteKing(pieces[Field.rightUp(kingPosition)]) ||
			isWhiteKing(pieces[Field.rightDown(kingPosition)]);
	}

	private static boolean up(int kingPosition, Piece[] pieces) {
		int to = Field.up(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.up(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteRook || piece.type() == WhiteQueen;
	}

	private static boolean down(int kingPosition, Piece[] pieces) {
		int to = Field.down(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.down(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteRook || piece.type() == WhiteQueen;
	}

	private static boolean right(int kingPosition, Piece[] pieces) {
		int to = Field.right(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.right(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteRook || piece.type() == WhiteQueen;
	}

	private static boolean left(int kingPosition, Piece[] pieces) {
		int to = Field.left(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.left(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteRook || piece.type() == WhiteQueen;
	}
	
	private static boolean rightUp(int kingPosition, Piece[] pieces) {
		int to = Field.rightUp(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightUp(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteBishop || piece.type() == WhiteQueen;
	}

	private static boolean rightDown(int kingPosition, Piece[] pieces) {
		int to = Field.rightDown(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightDown(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteBishop || piece.type() == WhiteQueen;
	}

	private static boolean leftUp(int kingPosition, Piece[] pieces) {
		int to = Field.leftUp(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftUp(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteBishop || piece.type() == WhiteQueen;
	}

	private static boolean leftDown(int kingPosition, Piece[] pieces) {
		int to = Field.leftDown(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftDown(to);
			piece = pieces[to];
		}
		
		return piece.type() == WhiteBishop || piece.type() == WhiteQueen;
	}

	private static boolean pawn(int kingPosition, Piece[] pieces) {
		return 
			isWhitePawn(pieces[Field.leftDown(kingPosition)]) ||
			isWhitePawn(pieces[Field.rightDown(kingPosition)]);
	}

	private static boolean knight(int kingPosition, Piece[] pieces) {
		return
			isWhiteKnight(pieces[leftUpUp(kingPosition)]) ||
			isWhiteKnight(pieces[rightUpUp(kingPosition)]) ||
			isWhiteKnight(pieces[leftLeftUp(kingPosition)]) ||
			isWhiteKnight(pieces[rightRightUp(kingPosition)]) ||
			isWhiteKnight(pieces[leftDownDown(kingPosition)]) ||
			isWhiteKnight(pieces[rightRightDown(kingPosition)]) ||
			isWhiteKnight(pieces[rightDownDown(kingPosition)]) ||
			isWhiteKnight(pieces[leftLeftDown(kingPosition)]);
	}

	
	private static boolean isWhitePawn(Piece piece) {
		return piece != null && piece.type() == WhitePawn;
	}
	
	private static boolean isWhiteKnight(Piece piece) {
		return piece != null && piece.type() == WhiteKnight;
	}
	
	private static boolean isWhiteKing(Piece piece) {
		return piece != null && piece.type() == WhiteKing;
	}
	
}
