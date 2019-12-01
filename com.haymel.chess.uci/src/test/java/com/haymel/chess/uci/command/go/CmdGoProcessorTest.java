/***************************************************
 * (c) Markus Heumel
 *
 * @date:	26.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static java.lang.Long.MAX_VALUE;
import static java.lang.String.format;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;

import com.haymel.chess.uci.Command;
import com.haymel.chess.uci.MovesImpl;
import com.haymel.chess.uci.lexer.Lexer;

public class CmdGoProcessorTest {

	private Command handler;
	
	@Before
	public void setup() {
		handler = mock(Command.class);
	}
	
	@Test 
	public void gowtime1winc2btime3binc4() {
		execute("wtime 1 winc 2 btime 3 binc 4");
		verify(handler, times(1)).go(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}

	private void execute(String string) {
		new CmdGoProcessor(new Lexer(string), handler).execute();
	}

	@Test 
	public void gowtime1btime3winc2binc4() {
		execute("wtime 1 btime 3 winc 2 binc 4");
		verify(handler, times(1)).go(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gowtime1winc2btime3binc4movestogo5() {
		execute("wtime 1 winc 2 btime 3 binc 4 movestogo 5");
		verify(handler, times(1)).go(1, 3, 2, 4, 5);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gomovestogo5wtime1winc2btime3binc4() {
		execute("movestogo 5 wtime 1 winc 2 btime 3 binc 4");
		verify(handler, times(1)).go(1, 3, 2, 4, 5);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void goponderwtime1winc2btime3binc4() {
		execute("ponder wtime 1 winc 2 btime 3 binc 4");
		verify(handler, times(1)).goPonder(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void goponderwtime1btime3winc2binc4() {
		execute("ponder wtime 1 btime 3 winc 2 binc 4");
		verify(handler, times(1)).goPonder(1, 3, 2, 4);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gomovetime30000() {
		execute("movetime 30000");
		verify(handler, times(1)).goMovetime(30000);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void godepth17() {
		execute("depth 17");
		verify(handler, times(1)).goDepth(17);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gonodes1000() {
		execute("nodes 1000");
		verify(handler, times(1)).goNodes(1000L);
		verifyNoMoreInteractions(handler);
	}
	
	@Test 
	public void gonodesMaxLong() {
		execute(format("nodes %s", MAX_VALUE));
		verify(handler, times(1)).goNodes(MAX_VALUE);
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void goinfinitesearchmovese2e4d2d4() {
		execute("infinite searchmoves e2e4 d2d4");
		verify(handler, times(1)).goInfinite(new MovesImpl().add("e2e4").add("d2d4"));
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void goinfinite() {
		execute("infinite");
		verify(handler, times(1)).goInfinite();
		verifyNoMoreInteractions(handler);
	}

	@Test 
	public void gomate5() {
		execute("mate 5");
		verify(handler, times(1)).goMate(5);
		verifyNoMoreInteractions(handler);
	}

}
