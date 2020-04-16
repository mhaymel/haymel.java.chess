/***************************************************
 * (c) Markus Heumel
 *
 * @date:	16.04.2019
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search;

public class DummyNodes implements Nodes {

	@Override
	public long count() {
		return 7;
	}

	@Override
	public long nps() {
		return 3;
	}

}
