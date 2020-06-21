/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	16.04.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.uciengine;

import static com.haymel.chess.engine.board.Field.e2;
import static com.haymel.chess.engine.board.Field.e4;
import static com.haymel.chess.engine.game.ActiveColor.black;
import static com.haymel.chess.engine.game.ActiveColor.white;
import static com.haymel.chess.engine.moves.Move.newMove;
import static com.haymel.chess.engine.moves.MoveType.normal;
import static com.haymel.chess.engine.search.SearchAlphaBeta.blackMate;
import static com.haymel.chess.engine.search.SearchAlphaBeta.whiteMate;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.haymel.chess.engine.search.BestMove;
import com.haymel.chess.engine.search.Variant;
import com.haymel.chess.uci.result.Infos;
import com.haymel.chess.uci.result.InfosUciString;

public class InfosFromBestMoveTest {

	private static final int aDepth = 10;
	private static final int aSelDepth = 15;
	private static final Variant variant = new Variant(newMove(e2, e4, normal));

	@Test
	public void activeColorWhiteWillBeMate() {
		BestMove bm = new BestMove(variant, whiteMate(2), aDepth, aSelDepth, new DummyNodes());
		Infos infos = new InfosFromBestMove(white, bm).value();
		assertThat(new InfosUciString(infos).value(), is("info score mate -2 depth 10 seldepth 15 nodes 7 nps 3 pv e2e4"));
	}

	@Test
	public void activeColorWhiteWillSetBlackMateInOneMove() {
		BestMove bm = new BestMove(variant, blackMate(1), aDepth, aSelDepth, new DummyNodes());
		Infos infos = new InfosFromBestMove(white, bm).value();
		assertThat(new InfosUciString(infos).value(), is("info score mate 1 depth 10 seldepth 15 nodes 7 nps 3 pv e2e4"));
	}
	
	@Test
	public void activeColorWhiteWillSetBlackMateInTwoMoves() {
		BestMove bm = new BestMove(variant, blackMate(3), aDepth, aSelDepth, new DummyNodes());
		Infos infos = new InfosFromBestMove(white, bm).value();
		assertThat(new InfosUciString(infos).value(), is("info score mate 2 depth 10 seldepth 15 nodes 7 nps 3 pv e2e4"));
	}
	
	@Test
	public void activeColorBlackWillBeMate() {
		BestMove bm = new BestMove(variant, blackMate(2), aDepth, aSelDepth, new DummyNodes());
		Infos infos = new InfosFromBestMove(black, bm).value();
		assertThat(new InfosUciString(infos).value(), is("info score mate -2 depth 10 seldepth 15 nodes 7 nps 3 pv e2e4"));
	}

	@Test
	public void activeColorBlackWillSetWhiteMateInOneMove() {
		BestMove bm = new BestMove(variant, whiteMate(1), aDepth, aSelDepth, new DummyNodes());
		Infos infos = new InfosFromBestMove(black, bm).value();
		assertThat(new InfosUciString(infos).value(), is("info score mate 1 depth 10 seldepth 15 nodes 7 nps 3 pv e2e4"));
	}
	
	@Test
	public void activeColorBlackWillSetWhiteMateInTwoMoves() {
		BestMove bm = new BestMove(variant, whiteMate(3), aDepth, aSelDepth, new DummyNodes());
		Infos infos = new InfosFromBestMove(black, bm).value();
		assertThat(new InfosUciString(infos).value(), is("info score mate 2 depth 10 seldepth 15 nodes 7 nps 3 pv e2e4"));
	}

}
