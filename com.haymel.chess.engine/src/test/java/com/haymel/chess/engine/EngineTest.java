/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	07.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import org.junit.Before;
import org.junit.Test;

public class EngineTest {

	private Engine engine;
	
	@Before
	public void setup() {
		engine = new Engine();
	}
	
	@Test
	public void e2e4_d7d5() {
		engine.move("e2e4");
		engine.move("d7d5");
	}

	@Test
	public void e2e3_e7e5() {
		engine.move("e2e3");
		engine.move("e7e5");
	}
	
}
