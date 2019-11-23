/***************************************************
 * (c) Markus Heumel
 *
 * @date:	17.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.util.Require.nonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UciWorker {

	private final BufferedReader in;
	private final CommandHandler cmdHandler;
	private volatile Thread thread;
	
	public UciWorker(BufferedReader in, CommandHandler cmdHandler) {
		this.in = nonNull(in, "in");
		this.cmdHandler = nonNull(cmdHandler, "cmdHandler");
	}

	public UciWorker(InputStream in, CommandHandler cmdHandler) {
		this(
			new BufferedReader(new InputStreamReader(nonNull(in, "in"))), 
			nonNull(cmdHandler, "cmdHandler"));
	}
	
	public synchronized void start() {
		if (thread != null)
			return;
		
		thread = new Thread(this::run);
		thread.start();
	}
	
	private void run() {
		while(thread != null)
			work();
	}
	
	private void work() {
		if (available())
			handleInput();
		else
			sleep();
	}

	private void sleep() {
		try {
			Thread.sleep(100);
		} 
		catch (InterruptedException e) {		//TODO
			e.printStackTrace();				
		}
	}

	private void handleInput() {
		try {
			new CommandProcessor(in.readLine(), cmdHandler).execute();
		} 
		catch (IOException e) {			//TODO
			e.printStackTrace();
		}
	}

	private boolean available() {
		try {
			return in.ready();
		} 
		catch (IOException e) {		//TODO
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized void stop() {
		if (thread == null)
			return;
				
		Thread t = thread;
		thread = null;
		join(t);
	}

	private void join(Thread t) {
		try {
			t.join();
		} 
		catch (InterruptedException e) {		//TODO
			e.printStackTrace();
		}
	}
	
}
