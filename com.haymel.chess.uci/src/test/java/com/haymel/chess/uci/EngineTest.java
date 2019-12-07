/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class EngineTest {

	@Test
	public void test() throws InterruptedException, UnsupportedEncodingException {
		
		ByteArrayInputStream in = new ByteArrayInputStream("uci\n".getBytes());
		
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream out = new PrintStream(baos, true, "UTF-8");

		Engine worker = new Engine(in, out);
		worker.start();
		Thread.sleep(10);
		worker.stop();
	}

}
