/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	14.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static org.junit.Assert.*;

import org.junit.Test;

public class StartposCreatorTest {

	@Test
	public void test() {
		Board board = new Board();
		new StartposCreator(board).execute();
	}

}
