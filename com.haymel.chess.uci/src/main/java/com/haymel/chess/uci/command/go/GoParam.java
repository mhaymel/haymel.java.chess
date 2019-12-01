/***************************************************
 * (c) Markus Heumel
 *
 * @date:	20.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.command.go;

import static com.haymel.chess.uci.command.IntParam.undefined;

import com.haymel.chess.uci.Moves;
import com.haymel.chess.uci.command.IntParam;
import com.haymel.chess.uci.command.LongParam;
import com.haymel.chess.uci.command.UnsignedInt;
import com.haymel.chess.uci.command.UnsignedLong;

final class GoParam {

	private boolean ponder = false;
	private SearchmovesParam searchmoves = SearchmovesParam.undefined;
	private IntParam wtime = undefined;
	private IntParam btime = undefined;
	private IntParam winc = undefined;
	private IntParam binc = undefined;
	private IntParam movestogo = undefined;
	private IntParam depth = undefined;
	private LongParam nodes = LongParam.undefined;
	private IntParam mate = undefined;
	private IntParam movetime = undefined;
	private boolean infinite = false;
	
	public boolean ponder() { 
		return ponder; 
	}
	
	public void setPonder() { 
		ponder = true; 
	}
	
	public SearchmovesParam searchmoves() { 
		return searchmoves; 
	}

	public void searchmoves(Moves moves) { 
		searchmoves = new SearchmovesParamImpl(moves);
	}
	
	public IntParam wtime() { 
		return wtime; 
	}
	
	public void wtime(int seconds) {
		wtime = new UnsignedInt(seconds);
	}
	
	public IntParam btime() { 
		return btime; 
	}

	public void btime(int seconds) {
		btime = new UnsignedInt(seconds);
	}
	
	public IntParam winc() { 
		return winc; 
	}

	public void winc(int seconds) {
		winc = new UnsignedInt(seconds);
	}

	public IntParam binc() { 
		return binc; 
	}

	public void binc(int seconds) {
		binc = new UnsignedInt(seconds);
	}

	public IntParam movestogo() { 
		return movestogo; 
	}

	public void movestogo(int moves) {
		movestogo = new UnsignedInt(moves);
	}
	
	public IntParam depth() { 
		return depth; 
	}

	public void depth(int depth) {
		this.depth = new UnsignedInt(depth);
	}
	
	public LongParam nodes() { 
		return nodes; 
	}

	public void nodes(long nodes) {
		this.nodes = new UnsignedLong(nodes);
	}
	
	public IntParam mate() { 
		return mate; 
	}

	public IntParam mate(int count) { 
		return mate = new UnsignedInt(count); 
	}
	
	public IntParam movetime() { 
		return movetime; 
	}

	public void movetime(int timeInMilliSeconds) {
		movetime = new UnsignedInt(timeInMilliSeconds);
	}
	
	public boolean infinite() { 
		return infinite; 
	}

	public void setInfinite() { 
		infinite = true; 
	}

}
