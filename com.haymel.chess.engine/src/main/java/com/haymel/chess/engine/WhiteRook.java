package com.haymel.chess.engine;

public final class WhiteRook implements Piece {

	@Override
	public boolean white() {
		return true;
	}

	@Override
	public boolean black() {
		return false;
	}

	@Override
	public boolean free() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Field field() {
		// TODO Auto-generated method stub
		return null;
	}

}
