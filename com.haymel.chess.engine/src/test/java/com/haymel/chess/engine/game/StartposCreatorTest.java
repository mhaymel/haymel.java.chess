/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.game;

import org.junit.Test;

public class StartposCreatorTest {

	@Test
	public void test() {
		Game game = new Game();
		new StartposCreator(game).execute();
	}

}
