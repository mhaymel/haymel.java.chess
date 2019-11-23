/***************************************************
 * (c) Markus Heumel
 *
 * @date:	20.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci.cmd.go;

import static com.haymel.chess.uci.cmd.IntParam.undefined;

import com.haymel.chess.uci.Moves;
import com.haymel.chess.uci.cmd.IntParam;
import com.haymel.chess.uci.cmd.UnsignedInt;

final class GoParam {

	private boolean ponder = false;
	private SearchmovesParam searchmoves = SearchmovesParam.undefined;
	private IntParam wtime = undefined;
	private IntParam btime = undefined;
	private IntParam winc = undefined;
	private IntParam binc = undefined;
	private IntParam movestogo = undefined;
	private IntParam depth = undefined;
	private IntParam nodes = undefined;
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
	
	public void wtime(long seconds) {
		wtime = new UnsignedInt(seconds);
	}
	
	public IntParam btime() { 
		return btime; 
	}

	public void btime(long seconds) {
		btime = new UnsignedInt(seconds);
	}
	
	public IntParam winc() { 
		return winc; 
	}

	public void winc(long seconds) {
		winc = new UnsignedInt(seconds);
	}

	public IntParam binc() { 
		return binc; 
	}

	public void binc(long seconds) {
		binc = new UnsignedInt(seconds);
	}

	public IntParam movestogo() { 
		return movestogo; 
	}

	public void movestogo(long moves) {
		movestogo = new UnsignedInt(moves);
	}
	
	public IntParam depth() { 
		return depth; 
	}

	public void depth(long depth) {
		this.depth = new UnsignedInt(depth);
	}
	
	public IntParam nodes() { 
		return nodes; 
	}

	public void nodes(long nodes) {
		this.nodes = new UnsignedInt(nodes);
	}
	
	public IntParam mate() { 
		return mate; 
	}

	public IntParam mate(long count) { 
		return mate = new UnsignedInt(count); 
	}
	
	public IntParam movetime() { 
		return movetime; 
	}

	public void movetime(long timeInMilliSeconds) {
		movetime = new UnsignedInt(timeInMilliSeconds);
	}
	
	public boolean infinite() { 
		return infinite; 
	}

	public void setInfinite() { 
		infinite = true; 
	}

}
