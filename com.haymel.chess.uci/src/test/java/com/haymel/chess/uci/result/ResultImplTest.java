/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.result;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;

import com.haymel.util.exception.HaymelNullPointerException;

public class ResultImplTest {

	private ByteArrayOutputStream baos;
	private PrintStream out;
	private ResultImpl result;
	
	@Before
	public void setup() throws UnsupportedEncodingException {
	    baos = new ByteArrayOutputStream();
	    out = new PrintStream(baos, true, "UTF-8");
	    result = new ResultImpl(out);
	}
	
	@Test(expected =  HaymelNullPointerException.class)
	public void constructorWithNullThrowsException() {
		new ResultImpl(null);
	}
	
	@Test
	public void testIdName() {
		result.idname("Haymel v0.01");
		assertOutput("id name Haymel v0.01");
	}

	@Test
	public void testIdAuthor() {
		result.idauthor("Markus Heumel");
		assertOutput("id author Markus Heumel");
	}
	
	@Test
	public void testUciok() {
		result.uciok();
		assertOutput("uciok");
	}
	
	@Test
	public void testReadyok() {
		result.readyok();
		assertOutput("readyok");
	}
	
	@Test
	public void testBestmove() {
		result.bestmove("e2e4");
		assertOutput("bestmove e2e4");
	}

	@Test
	public void testBestmovePonder() {
		result.bestmove("e2e4", "d7d5");
		assertOutput("bestmove e2e4 ponder d7d5");
	}

	@Test
	public void testCopyprotectionChecking() {
		result.copyprotectionChecking();
		assertOutput("copyprotection checking");
	}
	
	@Test
	public void testCopyprotectionOk() {
		result.copyprotectionOk();
		assertOutput("copyprotection ok");
	}
	
	@Test
	public void testCopyprotectionError() {
		result.copyprotectionError();
		assertOutput("copyprotection error");
	}
	
	@Test
	public void testRegistrationChecking() {
		result.registrationChecking();
		assertOutput("registration checking");
	}
	
	@Test
	public void testregistrationOk() {
		result.registrationOk();
		assertOutput("registration ok");
	}
	
	@Test
	public void testRegistrationError() {
		result.registrationError();
		assertOutput("registration error");
	}

	@Test
	public void testInfo() {
		result.info(new Infos().depth(12).nodes(123456).nps(100000));
		assertOutput("info depth 12 nodes 123456 nps 100000");
	}
	

	private void assertOutput(String string) {
		assertThat(output(), is(format("%s%n", string)));
	}

	private String output() {
	    return new String(baos.toByteArray(), StandardCharsets.UTF_8);
	}
}
