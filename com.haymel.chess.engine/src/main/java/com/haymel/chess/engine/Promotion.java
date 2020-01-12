/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	11.01.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

public enum Promotion {
	
	Queen,
	Rook,
	Bishop,
	Knight;
	
	public static Optional<Promotion> promotion(char character) {
		
		switch(character) {
		case 'q':
			return of(Queen);
		case 'r':
			return of(Rook);
		case 'b':
			return of(Bishop);
		case 'n':
			return of(Knight);
		}
		
		return empty();
	}
	
}
