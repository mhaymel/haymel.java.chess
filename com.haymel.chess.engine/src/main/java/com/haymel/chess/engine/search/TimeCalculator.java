/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	21.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.util.Require.greaterThanZero;
import static com.haymel.util.Require.nonNull;
import static java.lang.Math.max;
import static java.lang.Math.min;

import com.haymel.chess.engine.game.Game;

public class TimeCalculator {

	private final Game game;
	private int wtimeInMilliSeconds;
	private int btimeInMilliSeconds;
	
	public TimeCalculator(Game game, int wtimeInMilliSeconds, int btimeInMilliSeconds) {
		this.game = nonNull(game, "game");
		this.wtimeInMilliSeconds = greaterThanZero(wtimeInMilliSeconds, "wtimeInMilliSeconds");
		this.btimeInMilliSeconds = greaterThanZero(btimeInMilliSeconds, "btimeInMilliSeconds");
	}
	
	public int value() {
		switch(game.activeColor()) {
		case black: 	return max(1,  btimeInMilliSeconds / 30);
		case white: 	return max(1,  wtimeInMilliSeconds / 30);
		default:
			assert false;
			return max(1,  min(wtimeInMilliSeconds, btimeInMilliSeconds) / 30);
		}
	}
	
}
