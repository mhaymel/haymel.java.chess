/***************************************************
 * (c) Markus Heumel
 *
 * @date:	01.12.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.result;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.uci.moves.Moves;
import com.haymel.chess.uci.moves.MovesImpl;
import com.haymel.util.exception.HaymelIllegalArgumentException;
import com.haymel.util.exception.HaymelNullPointerException;

public class InfoTest {

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsKeyThrowsException() {
		new Info(null, "value");
	}

	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsMovesThrowsException() {
		new Info("key", (Moves)null);
	}
	
	@Test(expected = HaymelNullPointerException.class)
	public void constructorWithNullAsValueThrowsException() {
		new Info("key", (String)null);
	}
	
	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyKeyThrowsException() {
		new Info("", "value");
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyValueThrowsException() {
		new Info("key", "");
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyKeyAndNoValueThrowsException() {
		new Info("");
	}

	@Test(expected = HaymelIllegalArgumentException.class)
	public void constructorWithEmptyMovesThrowsException() {
		new Info("key", new MovesImpl());
	}
	
	@Test
	public void keyReturnsCorrectValue() {
		Info info = new Info("key1", new MovesImpl().add("e2e4"));
		assertThat(info.key(), is("key1"));
		
		info = new Info("key2", 1L);
		assertThat(info.key(), is("key2"));

		info = new Info("key3", 1);
		assertThat(info.key(), is("key3"));

		info = new Info("key4", "value");
		assertThat(info.key(), is("key4"));
		
		info = new Info("key5");
		assertThat(info.key(), is("key5"));
	}

	@Test
	public void valueReturnsCorrectValue() {
		Info info = new Info("key1", new MovesImpl().add("e2e4").add("d7d5"));
		assertThat(info.value(), is("e2e4 d7d5"));
		
		info = new Info("key2", 1L);
		assertThat(info.value(), is("1"));

		info = new Info("key3", 1);
		assertThat(info.value(), is("1"));

		info = new Info("key4", "value");
		assertThat(info.value(), is("value"));
		
		info = new Info("key9");
		assertThat(info.value(), is(""));
	}
	
}
