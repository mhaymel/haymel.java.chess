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
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;

import com.haymel.chess.engine.board.Field;
import com.haymel.chess.engine.piece.Piece;

public class WhiteInCheck {

	public static boolean whiteIsInCheck(int kingPosition, Piece[] pieces) {
		assert Field.valid(kingPosition);
		assert pieces[kingPosition].type() == WhiteKing;
	
		
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
			isBlackKing(pieces[Field.left(kingPosition)]) ||
			isBlackKing(pieces[Field.right(kingPosition)]) ||
			isBlackKing(pieces[Field.up(kingPosition)]) ||
			isBlackKing(pieces[Field.down(kingPosition)]) ||
			isBlackKing(pieces[Field.leftUp(kingPosition)]) ||
			isBlackKing(pieces[Field.leftDown(kingPosition)]) ||
			isBlackKing(pieces[Field.rightUp(kingPosition)]) ||
			isBlackKing(pieces[Field.rightDown(kingPosition)]);
	}

	private static boolean up(int kingPosition, Piece[] pieces) {
		int to = Field.up(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.up(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackRook || piece.type() == BlackQueen;
	}

	private static boolean down(int kingPosition, Piece[] pieces) {
		int to = Field.down(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.down(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackRook || piece.type() == BlackQueen;
	}

	private static boolean right(int kingPosition, Piece[] pieces) {
		int to = Field.right(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.right(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackRook || piece.type() == BlackQueen;
	}

	private static boolean left(int kingPosition, Piece[] pieces) {
		int to = Field.left(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.left(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackRook || piece.type() == BlackQueen;
	}
	
	private static boolean rightUp(int kingPosition, Piece[] pieces) {
		int to = Field.rightUp(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightUp(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackBishop || piece.type() == BlackQueen;
	}

	private static boolean rightDown(int kingPosition, Piece[] pieces) {
		int to = Field.rightDown(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.rightDown(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackBishop || piece.type() == BlackQueen;
	}

	private static boolean leftUp(int kingPosition, Piece[] pieces) {
		int to = Field.leftUp(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftUp(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackBishop || piece.type() == BlackQueen;
	}

	private static boolean leftDown(int kingPosition, Piece[] pieces) {
		int to = Field.leftDown(kingPosition);
		Piece piece = pieces[to];
		while(piece == null) {
			to = Field.leftDown(to);
			piece = pieces[to];
		}
		
		return piece.type() == BlackBishop || piece.type() == BlackQueen;
	}

	private static boolean pawn(int kingPosition, Piece[] pieces) {
		return 
			isBlackPawn(pieces[Field.leftUp(kingPosition)]) ||
			isBlackPawn(pieces[Field.rightUp(kingPosition)]);
	}

	private static boolean knight(int kingPosition, Piece[] pieces) {
		return
			isBlackKnight(pieces[leftUpUp(kingPosition)]) ||
			isBlackKnight(pieces[rightUpUp(kingPosition)]) ||
			isBlackKnight(pieces[leftLeftUp(kingPosition)]) ||
			isBlackKnight(pieces[rightRightUp(kingPosition)]) ||
			isBlackKnight(pieces[leftDownDown(kingPosition)]) ||
			isBlackKnight(pieces[rightRightDown(kingPosition)]) ||
			isBlackKnight(pieces[rightDownDown(kingPosition)]) ||
			isBlackKnight(pieces[leftLeftDown(kingPosition)]);
	}

	
	private static boolean isBlackPawn(Piece piece) {
		return piece != null && piece.type() == BlackPawn;
	}
	
	private static boolean isBlackKnight(Piece piece) {
		return piece != null && piece.type() == BlackKnight;
	}
	
	private static boolean isBlackKing(Piece piece) {
		return piece != null && piece.type() == BlackKing;
	}
	
}
