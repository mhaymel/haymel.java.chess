/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

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
	
}
