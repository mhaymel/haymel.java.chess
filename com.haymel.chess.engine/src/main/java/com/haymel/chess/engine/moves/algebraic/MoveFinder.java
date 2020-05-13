/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	06.01.2020
 * @author: Markus.Heumel
 *
 */

package com.haymel.chess.engine.moves.algebraic;

import static com.haymel.chess.engine.moves.algebraic.Promotion.Bishop;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Knight;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Queen;
import static com.haymel.chess.engine.moves.algebraic.Promotion.Rook;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;

import java.util.List;

import com.haymel.chess.engine.moves.Move;
import com.haymel.chess.engine.moves.Moves;

public class MoveFinder {

	private final Moves moves;
	
	public MoveFinder(Moves moves) {
		assert moves != null;
		this.moves = moves;
	}
	
	public Move move(String move) {
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
			switch(move.pieceType()) {
			case WhiteQueen:
			case BlackQueen:
				if (promotion == Queen)
					return move;
				break;
			case WhiteRook:
			case BlackRook:
				if (promotion == Rook)
					return move;
				break;
			case WhiteBishop:
			case BlackBishop:
				if (promotion == Bishop)
					return move;
				break;
			case WhiteKnight:
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

}
