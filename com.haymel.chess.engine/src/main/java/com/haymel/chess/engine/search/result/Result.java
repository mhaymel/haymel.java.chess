/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	09.03.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.search.result;


import static com.haymel.chess.engine.search.result.ResultType.InvalidBlackToMoveButMate;
import static com.haymel.chess.engine.search.result.ResultType.InvalidBlackToMoveButStalemate;
import static com.haymel.chess.engine.search.result.ResultType.InvalidBlackToMoveButWhiteInCheck;
import static com.haymel.chess.engine.search.result.ResultType.InvalidWhiteToMoveButBlackInCheck;
import static com.haymel.chess.engine.search.result.ResultType.InvalidWhiteToMoveButMate;
import static com.haymel.chess.engine.search.result.ResultType.InvalidWhiteToMoveButStalemate;
import static com.haymel.util.exception.HaymelException.throwHE;

import com.haymel.chess.engine.moves.Move;

public interface Result {
	
	Result invalidWhiteToMoveButMate = () -> InvalidWhiteToMoveButMate;
	Result invalidWhiteToMoveButStalemate = () -> InvalidWhiteToMoveButStalemate;
	Result invalidWhiteToMoveButBlackInCheck = () -> InvalidWhiteToMoveButBlackInCheck;
	Result invalidBlackToMoveButMate = () -> InvalidBlackToMoveButMate;
	Result invalidBlackToMoveButStalemate = () -> InvalidBlackToMoveButStalemate;
	Result invalidBlackToMoveButWhiteInCheck = () -> InvalidBlackToMoveButWhiteInCheck;
	
	ResultType type();
	
	default int value() {
		return throwHE("value() must not be called for type %s", type());
	}
	
	default Move[] moves() {
		return throwHE("moves() must not be called for type %s", type());
	}

}
