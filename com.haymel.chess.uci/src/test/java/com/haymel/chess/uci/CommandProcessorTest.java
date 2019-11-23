/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.MovesImpl.emptyMoves;
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

}
