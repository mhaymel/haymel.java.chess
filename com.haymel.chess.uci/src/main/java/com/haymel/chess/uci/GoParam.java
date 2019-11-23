/***************************************************
 * (c) Markus Heumel
 *
 * @date:	20.11.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uci;

import static com.haymel.chess.uci.IntParam.undefined;

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
	
	public boolean ponder() { return ponder; }
	public void setPonder() { ponder = true; }
	
	public SearchmovesParam searchmoves() { return searchmoves; }
	public IntParam wtime() { return wtime; }
	public IntParam btime() { return btime; }
	public IntParam winc() { return winc; }
	public IntParam binc() { return binc; }
	public IntParam movestogo() { return movestogo; }
	public IntParam depth() { return depth; }
	public IntParam nodes() { return nodes; }
	public IntParam mate() { return mate; }
	public IntParam movetime() { return movetime; }
	public boolean infinite() { return infinite; }

}
