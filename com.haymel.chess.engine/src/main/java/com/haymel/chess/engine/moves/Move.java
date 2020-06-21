/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	15.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.moves;

import static com.haymel.chess.engine.board.Field.fieldAsString;
import static com.haymel.chess.engine.moves.MoveType.capture;
import static com.haymel.chess.engine.moves.MoveType.captureKingMove;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.captureRookMove;
import static com.haymel.chess.engine.moves.MoveType.enpassant;
import static com.haymel.chess.engine.moves.MoveType.kingsideCastling;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.moves.MoveType.normalKingMove;
import static com.haymel.chess.engine.moves.MoveType.normalRookMove;
import static com.haymel.chess.engine.moves.MoveType.pawn;
import static com.haymel.chess.engine.moves.MoveType.pawnDoubleStep;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.moves.MoveType.queensideCastling;
import static com.haymel.chess.engine.moves.MoveType.validMoveType;
import static java.lang.String.format;

import com.haymel.chess.engine.board.Field;

public class Move {
	
	public static int newMove(int from, int to, int type) {
		assert Field.valid(from);
		assert Field.valid(to);
		assert validMoveType(type);
		assert from != to;

		return (from<<16)|(to<<8)|type;
	}
	
	public static int from(int move) {
		assert Field.valid(move>>16);
		return move>>16;
	}
	
	public static int to(int move) {
		assert Field.valid((move>>8) & 0b0000_0000_1111_1111);
		return (move>>8) & 0b0000_0000_1111_1111;
	}
	
	public static int type(int move) {
		assert validMoveType(move & 0b1111_1111);
		return move & 0b1111_1111;
	}
	
	public static String asString(int move) {
		assert validMove(move);
		
		switch(type(move)) {
		case normal: 
		case pawn:
		case pawnDoubleStep:	
		case normalRookMove:
		case normalKingMove:			return format("%s-%s", fieldAsString(from(move)), fieldAsString(to(move)));
		
		case captureRookMove:
		case captureKingMove:
		case capture: 					return format("%sx%s", fieldAsString(from(move)), fieldAsString(to(move)));
		
		case enpassant:					return format("%sx%se.p.", fieldAsString(from(move)), fieldAsString(to(move)));
		case capturePromotionQueen:		return format("%sx%sQ", fieldAsString(from(move)), fieldAsString(to(move)));
		case capturePromotionRook:		return format("%sx%sR", fieldAsString(from(move)), fieldAsString(to(move)));
		case capturePromotionBishop:	return format("%sx%sB", fieldAsString(from(move)), fieldAsString(to(move)));
		case capturePromotionKnight:	return format("%sx%sN", fieldAsString(from(move)), fieldAsString(to(move)));
		case kingsideCastling:			return "O-O";
		case queensideCastling:			return "O-O-O";
		case promotionQueen:			return format("%s-%sQ", fieldAsString(from(move)), fieldAsString(to(move)));
		case promotionRook:				return format("%s-%sR", fieldAsString(from(move)), fieldAsString(to(move)));
		case promotionBishop:			return format("%s-%sB", fieldAsString(from(move)), fieldAsString(to(move)));
		case promotionKnight:			return format("%s-%sN", fieldAsString(from(move)), fieldAsString(to(move)));
		default:
			assert false;
			throw new IllegalStateException(String.valueOf(type(move)));
		}
	}
	
	public static boolean capture(int move) {
		assert Move.validMove(move);
		return MoveType.capture(type(move));
	}

	public static boolean validMove(int move) {
		to(move);
		from(move);
		type(move);
		return true;
	}
	
}
