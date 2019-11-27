/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.MovesImpl.emptyMoves;
import static java.lang.Long.MAX_VALUE;
import static java.lang.String.format;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CommandProcessorTest {

	private CommandHandler handler;
	
	@Before
	public void setup() {
		handler = mock(CommandHandler.class);
	}
	
	@Test
	public void emptyStringDoesNotCallHandler() {
		new CommandProcessor("", handler).execute();
		verifyNoMoreInteractions(handler);
	}
	
	@Test
	public void stringContainingOnlyWhitespacesDoesNotCallHandler() {
		new CommandProcessor("  \t \t   ", handler).execute();
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void trailingAndLeadingWhitespacesWillBeIgnored() {
		new CommandProcessor("  uci   ", handler).execute();;
		verify(handler, times(1)).uci();

		new CommandProcessor("  isready   ", handler).execute();
		verify(handler, times(1)).isReady();

		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void stringWithUciCallsUci() {
		new CommandProcessor("uci", handler).execute();
		verify(handler, times(1)).uci();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void stringWithIsReadyCallsUci() {
		new CommandProcessor("isready", handler).execute();
		verify(handler, times(1)).isReady();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void debugOn() {
		new CommandProcessor("debug on", handler).execute();
		verify(handler, times(1)).debugOn();
		verify(handler, never()).debugOff();
	}

	@Test 
	public void debugOff() {
		new CommandProcessor("debug on", handler).execute();
		verify(handler, times(1)).debugOn();
		verify(handler, never()).debugOff();
	}

	@Test 
	public void debugMissingOnAndOff() {
		new CommandProcessor("debug", handler).execute();
		verify(handler, never()).debugOn();
		verify(handler, never()).debugOff();
		
		verify(handler, times(1)).unknown(Mockito.any());
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void debugWithUnknownMode() {
		new CommandProcessor("debug anything", handler).execute();
		verify(handler, never()).debugOn();
		verify(handler, never()).debugOff();
		
		verify(handler, times(1)).unknown(Mockito.any());
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void stringWithUciNewgameCallsUci() {
		new CommandProcessor("ucinewgame", handler).execute();
		verify(handler, times(1)).ucinewgame();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void testPosition() {
		new CommandProcessor("position startpos", handler).execute();
		verify(handler, times(1)).positionStart(emptyMoves);
	}

	@Test 
	public void testPositionWithMove() {
		new CommandProcessor("position startpos moves e2e4 e7e5", handler).execute();
		
		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("e7e5");
		
		verify(handler, times(1)).positionStart(moves);
	}

	@Test 
	public void testPositionWithFEN() {
		new CommandProcessor("position fen 8/4k3/8/8/8/6R1/4K3/8 b - - 0 1", handler).execute();
		verify(handler, times(1)).positionFen("8/4k3/8/8/8/6R1/4K3/8 b - - 0 1", emptyMoves);
	}
	
	@Test 
	public void testPositionWithFENWithMoves() {
		new CommandProcessor("position fen 8/4k3/8/8/8/6R1/4K3/8 b - - 0 1 moves e2e4 e7e5", handler).execute();

		MovesImpl moves = new MovesImpl();
		moves.add("e2e4").add("e7e5");

		verify(handler, times(1)).positionFen("8/4k3/8/8/8/6R1/4K3/8 b - - 0 1", moves);
	}

	@Test 
	public void stringWithStopCallsStop() {
		new CommandProcessor("stop", handler).execute();
		verify(handler, times(1)).stop();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void stringWithPonderhitCallsPonderhit() {
		new CommandProcessor("ponderhit", handler).execute();
		verify(handler, times(1)).ponderhit();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void stringWithQuitCallsQuit() {
		new CommandProcessor("quit", handler).execute();
		verify(handler, times(1)).quit();
		verifyNoMoreInteractions(handler);
	}

	
	@Test 
	public void gowtime1winc2btime3binc4() {
		new CommandProcessor("go wtime 1 winc 2 btime 3 binc 4", handler).execute();
		verify(handler, times(1)).go(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void gowtime1btime3winc2binc4() {
		new CommandProcessor("go wtime 1 btime 3 winc 2 binc 4", handler).execute();
		verify(handler, times(1)).go(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gowtime1winc2btime3binc4movestogo5() {
		new CommandProcessor("go wtime 1 winc 2 btime 3 binc 4 movestogo 5", handler).execute();
		verify(handler, times(1)).go(1, 3, 2, 4, 5);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gomovestogo5wtime1winc2btime3binc4() {
		new CommandProcessor("go movestogo 5 wtime 1 winc 2 btime 3 binc 4", handler).execute();
		verify(handler, times(1)).go(1, 3, 2, 4, 5);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void goponderwtime1winc2btime3binc4() {
		new CommandProcessor("go ponder wtime 1 winc 2 btime 3 binc 4", handler).execute();
		verify(handler, times(1)).goPonder(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void goponderwtime1btime3winc2binc4() {
		new CommandProcessor("go ponder wtime 1 btime 3 winc 2 binc 4", handler).execute();
		verify(handler, times(1)).goPonder(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gomovetime30000() {
		new CommandProcessor("go movetime 30000", handler).execute();
		verify(handler, times(1)).goMovetime(30000);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void godepth17() {
		new CommandProcessor("go depth 17", handler).execute();
		verify(handler, times(1)).goDepth(17);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gonodes1000() {
		new CommandProcessor("go nodes 1000", handler).execute();
		verify(handler, times(1)).goNodes(1000);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gonodesMaxLong() {
		new CommandProcessor(format("go nodes %s", MAX_VALUE), handler).execute();
		verify(handler, times(1)).goNodes(MAX_VALUE);
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void goinfinitesearchmovese2e4d2d4() {
		new CommandProcessor("go infinite searchmoves e2e4 d2d4", handler).execute();
		verify(handler, times(1)).goInfinite(new MovesImpl().add("e2e4").add("d2d4"));
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void goinfinite() {
		new CommandProcessor("go infinite", handler).execute();
		verify(handler, times(1)).goInfinite();
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void gomate5() {
		new CommandProcessor("go mate 5", handler).execute();
		verify(handler, times(1)).goMate(5);
		verifyNoMoreInteractions(handler);
	}
	
}
