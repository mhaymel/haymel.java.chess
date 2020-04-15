/***************************************************
 * (c) Markus Heumel
 *
 * @date: 	10.02.2020
 * @author: Markus.Heumel
 *
 */
package com.haymel.chess.engine.fen;

import static com.haymel.chess.engine.board.Field.field;
import static com.haymel.chess.engine.piece.PieceType.BlackBishop;
import static com.haymel.chess.engine.piece.PieceType.BlackKing;
import static com.haymel.chess.engine.piece.PieceType.BlackKnight;
import static com.haymel.chess.engine.piece.PieceType.BlackPawn;
import static com.haymel.chess.engine.piece.PieceType.BlackQueen;
import static com.haymel.chess.engine.piece.PieceType.BlackRook;
import static com.haymel.chess.engine.piece.PieceType.WhiteBishop;
import static com.haymel.chess.engine.piece.PieceType.WhiteKing;
import static com.haymel.chess.engine.piece.PieceType.WhiteKnight;
import static com.haymel.chess.engine.piece.PieceType.WhitePawn;
import static com.haymel.chess.engine.piece.PieceType.WhiteQueen;
import static com.haymel.chess.engine.piece.PieceType.WhiteRook;
import static com.haymel.util.Require.nonNull;
import static com.haymel.util.exception.HaymelIllegalArgumentException.throwIAE;
import static java.util.Collections.unmodifiableMap;
import static org.apache.commons.lang3.ArrayUtils.reverse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.haymel.chess.engine.piece.Piece;
import com.haymel.chess.engine.piece.PieceType;

//  rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
//  r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R

public class PiecePlacement {

	private static final Map<Character, PieceType> charToPieceType = create();
	
	private String[] ranks;
	
	public PiecePlacement(String piecePlacement) {
		this.ranks = rank(nonNull(piecePlacement, "piecePlacement"));
	}
	
	public List<Piece> value() {
		List<Piece> pieces = new ArrayList<>();
		
		for(int rankNo = 0; rankNo < 8; rankNo++)
			add(rankNo, pieces);
		
		return pieces;
	}
	
	private void add(int rankNo, List<Piece> pieces) {
		String rank = ranks[rankNo];
		int fileNo = 0;
		for(int i = 0; i < rank.length(); i++) {
			char c = rank.charAt(i);
			switch(c) {
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
				fileNo += (c - '1' + 1); 
				assert fileNo <= 8;
				break;
			default:
				pieces.add(piece(fileNo, rankNo, c));
				fileNo++;
				assert fileNo <= 8;
				break;
			}
		}
		
		assert fileNo == 8;
	}

	private Piece piece(int file, int rank, char c) {
		int field = field(file, rank);
		PieceType type = charToPieceType.get(c);

		if (type == null)
			return throwIAE("cannot handle piece '%'", c);
		
		return new Piece(type, field);
	}

	private static String[] rank(String piecePlacement) {
		String trimmed = piecePlacement.trim();
		if (trimmed.isEmpty())
			return throwIAE("piecePlacement must not be empty");

		verifyCharacters(trimmed);
		
		String[] splitted = trimmed.split("/");
		if (splitted.length != 8)
			return throwIAE("piece placement must have exactly 8 ranks but has %s in '%s'", splitted.length, piecePlacement);
		
		reverse(splitted);
		return splitted;
	}

	private static void verifyCharacters(String piecePlacement) {
		for(int i = 0; i < piecePlacement.length(); i++) {
			char c = piecePlacement.charAt(i);
			switch(c) {
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case 'p':
			case 'r':
			case 'n':
			case 'b':
			case 'q':
			case 'k':
			case 'P':
			case 'R':
			case 'N':
			case 'B':
			case 'Q':
			case 'K':
			case '/':
				break;
			default:
				throwIAE("piecePlacement contains illegal character '%s': %s", c, piecePlacement);
			}
		}
	}

	private static Map<Character, PieceType> create() {
		Map<Character, PieceType> map = new HashMap<Character, PieceType>();
	
		map.put('p', BlackPawn);
		map.put('r', BlackRook);
		map.put('n', BlackKnight);
		map.put('b', BlackBishop);
		map.put('q', BlackQueen);
		map.put('k', BlackKing);

		map.put('P', WhitePawn);
		map.put('R', WhiteRook);
		map.put('N', WhiteKnight);
		map.put('B', WhiteBishop);
		map.put('Q', WhiteQueen);
		map.put('K', WhiteKing);
		
		return unmodifiableMap(map); 
	}
	
}
