/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

public class UciWorkerTest {

	private Command cmdHandler;
	
	@Before
	public void setup() {
		cmdHandler = mock(Command.class);
	}
	
	@Test
	public void test() throws InterruptedException {
		
		ByteArrayInputStream in = new ByteArrayInputStream("uci\n".getBytes());
		UciWorker worker = new UciWorker(in, cmdHandler);
		worker.start();
		Thread.sleep(10);
		worker.stop();
		verify(cmdHandler, times(1)).uci();
	}

}
