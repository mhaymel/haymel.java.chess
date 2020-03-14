/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.result;

public enum ResultType {
	Normal, 
	MateInMoves,
	
	InvalidWhiteToMoveButMate,
	InvalidWhiteToMoveButStalemate,
	InvalidWhiteToMoveButBlackInCheck,
	
	InvalidBlackToMoveButMate,
	InvalidBlackToMoveButStalemate,
	InvalidBlackToMoveButWhiteInCheck,
}
