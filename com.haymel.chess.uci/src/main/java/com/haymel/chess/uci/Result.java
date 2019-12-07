/***************************************************
 * (c) Markus Heumel
 *
 * @date:	28.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import com.haymel.chess.uci.result.Infos;

/*
 * The comments below were copied from 
 * 		"Description of the universal chess interface (UCI) April  2006" 
 * 		by  Stefan Meyer-Kahlen
 */

/*
		Description of the universal chess interface (UCI)    April  2006
		=================================================================
		
		* The specification is independent of the operating system. For Windows,
		  the engine is a normal exe file, either a console or "real" windows application.
		
		* all communication is done via standard input and output with text commands,
		
		* The engine should boot and wait for input from the GUI,
		  the engine should wait for the "isready" or "setoption" command to set up its internal parameters
		  as the boot process should be as quick as possible.
		
		* the engine must always be able to process input from stdin, even while thinking.
		
		* all command strings the engine receives will end with '\n',
		  also all commands the GUI receives should end with '\n',
		  Note: '\n' can be 0x0d or 0x0a0d or any combination depending on your OS.
		  If you use Engine and GUI in the same OS this should be no problem if you communicate in text mode,
		  but be aware of this when for example running a Linux engine in a Windows GUI.
		
		* arbitrary white space between tokens is allowed
		  Example: "debug on\n" and  "   debug     on  \n" and "\t  debug \t  \t\ton\t  \n"
		  all set the debug mode of the engine on.
		
		* The engine will always be in forced mode which means it should never start calculating
		  or pondering without receiving a "go" command first.
		
		* Before the engine is asked to search on a position, there will always be a position command
		  to tell the engine about the current position.
		
		* by default all the opening book handling is done by the GUI,
		  but there is an option for the engine to use its own book ("OwnBook" option, see below)
		
		* if the engine or the GUI receives an unknown command or token it should just ignore it and try to
		  parse the rest of the string in this line.
		  Examples: "joho debug on\n" should switch the debug mode on given that joho is not defined,
		            "debug joho on\n" will be undefined however.
		
		* if the engine receives a command which is not supposed to come, for example "stop" when the engine is
		  not calculating, it should also just ignore it.
		  
		
		Move format:
		------------
		
		The move format is in long algebraic notation.
		A nullmove from the Engine to the GUI should be sent as 0000.
		Examples:  e2e4, e7e5, e1g1 (white short castling), e7e8q (for promotion)
*/
public interface Result {

	/*
		id
			* name <x>
				this must be sent after receiving the "uci" command to identify the engine,
				e.g. "id name Shredder X.Y\n"
			* author <x>
				this must be sent after receiving the "uci" command to identify the engine,
				e.g. "id author Stefan MK\n"
		
		e.g.
		id name SOS 5 for Arena
		id author Rudolf Huber
	*/
	void idname(String name);		
	void idauthor(String author);

	/*
	 	uciok
			Must be sent after the id and optional options to tell the GUI that the engine
			has sent all infos and is ready in uci mode.
	*/
	void uciok();
	
	/*
	 	readyok
			This must be sent when the engine has received an "isready" command and has
			processed all input and is ready to accept new commands now.
			It is usually sent after a command that can take some time to be able to wait for the engine,
			but it can be used anytime, even when the engine is searching,
			and must always be answered with "isready".	
	*/
	void readyok();

	/*
		bestmove <move1> [ ponder <move2> ]
			the engine has stopped searching and found the move <move> best in this position.
			the engine can send the move it likes to ponder on. The engine must not start pondering automatically.
			this command must always be sent if the engine stops searching, also in pondering mode if there is a
			"stop" command, so for every "go" command a "bestmove" command is needed!
			Directly before that the engine should send a final "info" command with the final search information,
			the the GUI has the complete statistics about the last search.
	*/
	void bestmove(String move);
	void bestmove(String move, String ponderMove);
	
	/*
		copyprotection
			this is needed for copyprotected engines. After the uciok command the engine can tell the GUI,
			that it will check the copy protection now. This is done by "copyprotection checking".
			If the check is ok the engine should send "copyprotection ok", otherwise "copyprotection error".
			If there is an error the engine should not function properly but should not quit alone.
			If the engine reports "copyprotection error" the GUI should not use this engine
			and display an error message instead!
			The code in the engine can look like this
		      TellGUI("copyprotection checking\n");
			   // ... check the copy protection here ...
			   if(ok)
			      TellGUI("copyprotection ok\n");
		      else
		         TellGUI("copyprotection error\n");
   	*/
	void copyprotectionChecking();
	void copyprotectionOk();
	void copyprotectionError();
	
	/*
		registration
			this is needed for engines that need a username and/or a code to function with all features.
			Analog to the "copyprotection" command the engine can send "registration checking"
			after the uciok command followed by either "registration ok" or "registration error".
			Also after every attempt to register the engine it should answer with "registration checking"
			and then either "registration ok" or "registration error".
			In contrast to the "copyprotection" command, the GUI can use the engine after the engine has
			reported an error, but should inform the user that the engine is not properly registered
			and might not use all its features.
			In addition the GUI should offer to open a dialog to
			enable registration of the engine. To try to register an engine the GUI can send
			the "register" command.
			The GUI has to always answer with the "register" command	if the engine sends "registration error"
			at engine startup (this can also be done with "register later")
			and tell the user somehow that the engine is not registered.
			This way the engine knows that the GUI can deal with the registration procedure and the user
			will be informed that the engine is not properly registered.
	*/
	void registrationChecking();
	void registrationOk();
	void registrationError();
	
	/*
		info
			the engine wants to send information to the GUI. This should be done whenever one of the info has changed.
			The engine can send only selected infos or multiple infos with one info command,
			e.g. "info currmove e2e4 currmovenumber 1" or
			     "info depth 12 nodes 123456 nps 100000".
			Also all infos belonging to the pv should be sent together
			e.g. "info depth 2 score cp 214 time 1242 nodes 2124 nps 34928 pv e2e4 e7e5 g1f3"
			I suggest to start sending "currmove", "currmovenumber", "currline" and "refutation" only after one second
			to avoid too much traffic.
			Additional info:
			* depth <x>
				search depth in plies
			* seldepth <x>
				selective search depth in plies,
				if the engine sends seldepth there must also be a "depth" present in the same string.
			* time <x>
				the time searched in ms, this should be sent together with the pv.
			* nodes <x>
				x nodes searched, the engine should send this info regularly
			* pv <move1> ... <movei>
				the best line found
			* multipv <num>
				this for the multi pv mode.
				for the best move/pv add "multipv 1" in the string when you send the pv.
				in k-best mode always send all k variants in k strings together.
			* score
				* cp <x>
					the score from the engine's point of view in centipawns.
				* mate <y>
					mate in y moves, not plies.
					If the engine is getting mated use negative values for y.
				* lowerbound
			      the score is just a lower bound.
				* upperbound
				   the score is just an upper bound.
			* currmove <move>
				currently searching this move
			* currmovenumber <x>
				currently searching move number x, for the first move x should be 1 not 0.
			* hashfull <x>
				the hash is x permill full, the engine should send this info regularly
			* nps <x>
				x nodes per second searched, the engine should send this info regularly
			* tbhits <x>
				x positions where found in the endgame table bases
			* sbhits <x>
				x positions where found in the shredder endgame databases
			* cpuload <x>
				the cpu usage of the engine is x permill.
			* string <str>
				any string str which will be displayed be the engine,
				if there is a string command the rest of the line will be interpreted as <str>.
			* refutation <move1> <move2> ... <movei>
			   move <move1> is refuted by the line <move2> ... <movei>, i can be any number >= 1.
			   Example: after move d1h5 is searched, the engine can send
			   "info refutation d1h5 g6h5"
			   if g6h5 is the best answer after d1h5 or if g6h5 refutes the move d1h5.
			   if there is no refutation for d1h5 found, the engine should just send
			   "info refutation d1h5"
				The engine should only send this if the option "UCI_ShowRefutations" is set to true.
			* currline <cpunr> <move1> ... <movei>
			   this is the current line the engine is calculating. <cpunr> is the number of the cpu if
			   the engine is running on more than one cpu. <cpunr> = 1,2,3....
			   if the engine is just using one cpu, <cpunr> can be omitted.
			   If <cpunr> is greater than 1, always send all k lines in k strings together.
				The engine should only send this if the option "UCI_ShowCurrLine" is set to true.
	*/
	void info(Infos infos);
}
