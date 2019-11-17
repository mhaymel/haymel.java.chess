/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CmdPositionProcessorTest {

	private CommandHandler handler;

	@Before
	public void setup() {
		handler = mock(CommandHandler.class);
	}
	
	@Test
	public void test() {
		Parser parser = new Parser("position startpos moves e2e4 e7e5");
		
		List<String> moves = new ArrayList<>();
		moves.add("e2e4");
		moves.add("e7e5");
		
		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionStart(moves);
	}

}
