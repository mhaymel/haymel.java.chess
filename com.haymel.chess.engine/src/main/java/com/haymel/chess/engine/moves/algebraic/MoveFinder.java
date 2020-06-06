/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.01.2020
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.engine.moves.algebraic;

import static com.haymel.chess.engine.moves.MoveType.capturePromotionBishop;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionKnight;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionQueen;
import static com.haymel.chess.engine.moves.MoveType.capturePromotionRook;
import static com.haymel.chess.engine.moves.MoveType.promotionBishop;
import static com.haymel.chess.engine.moves.MoveType.promotionKnight;
import static com.haymel.chess.engine.moves.MoveType.promotionQueen;
import static com.haymel.chess.engine.moves.MoveType.promotionRook;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Bishop;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Knight;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Queen;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Rook;

import java.util.List;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;

public class MoveFinder {

	private final Moves moves;
	
	public MoveFinder(Moves moves) {
		assert moves != null;
		this.moves = moves;
	}
	
	public Move find(String move) {
		FieldsFromAlgebraicMove fields = new FieldsFromAlgebraicMove(move);
		List<Move> m = moves.findMoves(fields.from(), fields.to());

		if (m.isEmpty())
			return null;

		if (!fields.isPromotion()) 
			return m.get(0);

		return promotion(fields.promotion(), m);
	}
	
	private Move promotion(Promotion promotion, List<Move> moves) {
		for (Move move : moves) {
			switch(move.type()) {
			case promotionQueen:
			case capturePromotionQueen:
				if (promotion == Queen)
					return move;
				break;
			case promotionRook:
			case capturePromotionRook:
				if (promotion == Rook)
					return move;
				break;
			case promotionBishop:
			case capturePromotionBishop:
				if (promotion == Bishop)
					return move;
				break;
			case promotionKnight:
			case capturePromotionKnight:
				if (promotion == Knight)
					return move;
				break;
			default:
				assert false : move.toString();
				break;
			}
		}
		return null;
	}

}
