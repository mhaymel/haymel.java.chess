package com.haymel.chess.engine.uci;
import static com.haymel.util.Require.nonNull;

import java.io.InputStream;
import java.io.OutputStream;

/***************************************************
 * (c) Markus Heumel
 *
 * @date:	11.11.2019
 * @author: Markus.Heumel
 *
 */
public class UciEngine {

	private final InputStream in;
	private final OutputStream out;
	
	public UciEngine(InputStream in, OutputStream out) {
		this.in = nonNull(in, "in");
		this.out = nonNull(out, "out");
	}
	
	
}
