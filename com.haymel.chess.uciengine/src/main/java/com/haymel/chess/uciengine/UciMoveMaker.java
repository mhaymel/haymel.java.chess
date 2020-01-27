/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.01.2020
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.uciengine;

import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.uciengine.Promotion.Bishop;
import static com.haymel.chess.uciengine.Promotion.Knight;
import static com.haymel.chess.uciengine.Promotion.Queen;
import static com.haymel.chess.uciengine.Promotion.Rook;
import static com.haymel.util.Require.nonNull;
import static java.lang.String.format;

import java.util.List;

import com.haymel.chess.engine.game.Game;
import com.haymel.chess.engine.game.MakeMove;
import com.haymel.chess.engine.moves.Move;

public class UciMoveMaker {

	private final Game game;
	
	public UciMoveMaker(Game game) {
		this.game = nonNull(game, "game");
	}
	
	public void move(String moveAsString) {
		FieldsFromUciMoveString fields = new FieldsFromUciMoveString(moveAsString);
		if (game.activeColor() == white)
			moveWhite(fields);
		else
			moveBlack(fields);
	}

	private void moveBlack(FieldsFromUciMoveString fields) {
		List<Move> moves = game.blackMoves().findMoves(fields.from(), fields.to());
		
		if (moves.isEmpty())
			throw new IllegalStateException(format("cannot find a move %s%s", fields.from(), fields.to()));
		
		if (fields.isPromotion()) {
			make(blackPromotionMove(fields.promotion(), moves));
		}
		else {
			assert moves.size() == 1;
			make(moves.get(0));
		}
	}

	private Move blackPromotionMove(Promotion promotion, List<Move> moves) {
		for (Move move : moves) {
			switch(move.pieceType()) {
			case BlackQueen:
				if (promotion == Queen)
					return move;
				break;
			case BlackRook:
				if (promotion == Rook)
					return move;
				break;
			case BlackBishop:
				if (promotion == Bishop)
					return move;
				break;
			case BlackKnight:
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

	private void moveWhite(FieldsFromUciMoveString fields) {
		List<Move> moves = game.whiteMoves().findMoves(fields.from(), fields.to());

		if (moves.isEmpty())
			throw new IllegalStateException(format("cannot find a move %s%s", fields.from(), fields.to()));
		
		if (fields.isPromotion()) {
			make(whitePromotionMove(fields.promotion(), moves));
		}
		else {
			assert moves.size() == 1;
			make(moves.get(0));
		}
	}

	private Move whitePromotionMove(Promotion promotion, List<Move> moves) {
		for (Move move : moves) {
			switch(move.pieceType()) {
			case WhiteQueen:
				if (promotion == Queen)
					return move;
				break;
			case WhiteRook:
				if (promotion == Rook)
					return move;
				break;
			case WhiteBishop:
				if (promotion == Bishop)
					return move;
				break;
			case WhiteKnight:
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

	private void make(Move move) {
		new MakeMove(game).makeMove(move);
	}

}
