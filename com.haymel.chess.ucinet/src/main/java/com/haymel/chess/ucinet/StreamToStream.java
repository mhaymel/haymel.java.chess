package com.haymel.chess.ucinet;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamToStream implements Runnable {

	private final InputStream in;
	private final OutputStream out;
	
	public StreamToStream(InputStream in, OutputStream out) {
		this.in = requireNonNull(in);
		this.out = requireNonNull(out);
	}
	
	public void execute() {
	}

	@Override
	public void run() {
		try {
			in.transferTo(out);
		} 
		catch (IOException e) {
			System.out.println("exception: " + e);
		}
	}
	
}
