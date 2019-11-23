/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.MovesImpl.emptyMoves;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class CmdPositionProcessorTest {

	private CommandHandler handler;

	@Before
	public void setup() {
		handler = mock(CommandHandler.class);
	}

	@Test
	public void positionWithoutMove() {
		Parser parser = new Parser("position startpos");
		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionStart(emptyMoves);
	}

	@Test
	public void positionWithoutMoves() {
		Parser parser = new Parser("position startpos moves");
		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionStart(emptyMoves);
	}

	@Test
	public void positionWithMoves() {
		Parser parser = new Parser("position startpos moves e2e4 e7e5");
		
		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("e7e5");
		
		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionStart(moves);
	}

	@Test
	public void positionFenWithoutMove() {
		Parser parser = new Parser("position fen 8/4k3/8/8/8/6R1/4K3/8 b - - 0 1");
		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionFen("8/4k3/8/8/8/6R1/4K3/8 b - - 0 1", emptyMoves);
	}

	@Test
	public void positionFenWithoutMoves() {
		Parser parser = new Parser("position fen 8/4k3/8/8/8/6R1/4K3/8 b - - 0 1 moves");
		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionFen("8/4k3/8/8/8/6R1/4K3/8 b - - 0 1", emptyMoves);
	}

	@Test
	public void positionFenWitMoves() {
		Parser parser = new Parser("position fen 8/4k3/8/8/8/6R1/4K3/8 b - - 0 1 moves e2e4 e7e5");
		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("e7e5");

		new CmdPositionProcessor(parser, handler).execute();
		verify(handler, times(1)).positionFen("8/4k3/8/8/8/6R1/4K3/8 b - - 0 1", moves);
	}
	
}
