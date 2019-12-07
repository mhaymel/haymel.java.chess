package com.haymel.chess.engine.uci;

import com.haymel.chess.uci.command.Command;

public interface Engine {

	Command uciCommandHandler();
	
}
