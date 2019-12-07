/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

public class EngineTest {

    private ByteArrayOutputStream baos;
    private PrintStream out;
    private Engine engine;

    @Before
    public void setup() throws UnsupportedEncodingException {
	    baos = new ByteArrayOutputStream();
	    out = new PrintStream(baos, true, "UTF-8");
    }
    
    @Test
    public void testUci() throws InterruptedException {
    	ByteArrayInputStream in = new ByteArrayInputStream(format("uci%n").getBytes());
    	engine = new FakeEngine(in, out);
    	engine.start();
    	Thread.sleep(10);
    	String result = baos.toString(Charset.forName("UTF-8"));
    	assertThat(result, is(String.format("id author Markus Heumel%nid name haymel%nuciok%n")));
    }
    
    @Test
    public void testIsReady() throws InterruptedException {
    	ByteArrayInputStream in = new ByteArrayInputStream(format("isready%n").getBytes());
    	engine = new FakeEngine(in, out);
    	engine.start();
    	Thread.sleep(10);
    	String result = baos.toString(Charset.forName("UTF-8"));
    	assertThat(result, is(String.format("readyok%n")));
    }
    
    @Test
    public void testPositionAndGo() throws InterruptedException {
    	ByteArrayInputStream in = new ByteArrayInputStream(format("position startpos moves b1c3 b8c6%ngo wtime 100000 btime 100000 winc 1 binc 0%n").getBytes());
    	engine = new FakeEngine(in, out);
    	engine.start();
    	Thread.sleep(1000);
    	String result = baos.toString(Charset.forName("UTF-8"));
    	assertThat(result, is(String.format("bestmove a1b1%n")));
    }
}
