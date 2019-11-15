package com.haymel.chess.uci;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CommandProcessorTest {

	private CommandProcessor processor;
	private CommandHandler handler;
	
	@Before
	public void setup() {
		processor = new CommandProcessor();
		handler = Mockito.mock(CommandHandler.class);
	}
	
	@Test
	public void emptyStringDoesNotCallHandler() {
		processor.execute("", handler);
		Mockito.verifyNoMoreInteractions(handler);
	}
	
	@Test
	public void stringContainingOnlyWhitespacesDoesNotCallHandler() {
		processor.execute("  \t \t   ", handler);
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void trailingAndLeadingWhitespacesWillBeIgnored() {
		processor.execute("  uci   ", handler);
		verify(handler, times(1)).uci();

		processor.execute("  isready   ", handler);
		verify(handler, times(1)).isReady();

		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void stringWithUciCallsUci() {
		processor.execute("uci", handler);
		verify(handler, times(1)).uci();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void stringWithIsReadyCallsUci() {
		processor.execute("isready", handler);
		verify(handler, times(1)).isReady();
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void debugOn() {
		processor.execute("debug on", handler);
		verify(handler, times(1)).debugOn();
		verify(handler, never()).debugOff();
	}

	@Test 
	public void debugOff() {
		processor.execute("debug on", handler);
		verify(handler, times(1)).debugOn();
		verify(handler, never()).debugOff();
	}

	@Test 
	public void debugMissingOnAndOff() {
		processor.execute("debug", handler);
		verify(handler, never()).debugOn();
		verify(handler, never()).debugOff();
		
		verify(handler, times(1)).unknown(Mockito.any());
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void debugWithUnknownMode() {
		processor.execute("debug anything", handler);
		verify(handler, never()).debugOn();
		verify(handler, never()).debugOff();
		
		verify(handler, times(1)).unknown(Mockito.any());
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void stringWithUciNewgameCallsUci() {
		processor.execute("ucinewgame", handler);
		verify(handler, times(1)).ucinewgame();
		verifyNoMoreInteractions(handler);
	}
	
	
	
}
