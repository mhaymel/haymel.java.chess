package com.haymel.chess.uci;

import static java.util.Objects.requireNonNull;

public class CommandProcessor {

	public void execute(String line, CommandHandler handler) {
		requireNonNull(line);
		requireNonNull(handler);
		
		if (line.trim().length() == 0)
			return;
		
		doExecute(line.trim().split("\\s+"), handler);
	}

	private void doExecute(String[] command, CommandHandler handler) {
		if (command.length == 0)
			return;
		
		if (is("uci", command))
			handler.uci();

		else if (is("debug", command))
			handleDebug(command, handler);

		else if(is("isready", command))
			handler.isReady();
	
		else
			handler.unknown(command);

	}
	
	private void handleDebug(String[] command, CommandHandler handler) {
		if (firstParamIs("on", command))
			handler.debugOn();
		
		else if (firstParamIs("off", command))
			handler.debugOff();
			
		else
			handler.unknown(command);
	}

	private boolean firstParamIs(String expected, String[] received) {
		return hasAtLeastOneParameter(received) && is(expected, received[1]);
	}

	private boolean hasAtLeastOneParameter(String[] received) {
		return received.length >= 2;
	}

	private boolean is(String command, String[] commandLine) {
		return is(command, commandLine[0]);
	}

	private boolean is(String expected, String received) {
		return expected.toLowerCase().equals(received.toLowerCase());
	}
	
}
