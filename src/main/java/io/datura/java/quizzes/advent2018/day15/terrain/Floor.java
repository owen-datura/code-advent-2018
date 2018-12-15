package io.datura.java.quizzes.advent2018.day15.terrain;

public class Floor extends Terrain {
	public static final Floor FLOOR = new Floor();

	private Floor() {
	}

	@Override
	public char getOutputSymbol() {
		return '.';
	}

	@Override
	public boolean canBeMoved() {
		return false;
	}

	@Override
	public boolean canBeOverwritten() {
		return true;
	}
}
