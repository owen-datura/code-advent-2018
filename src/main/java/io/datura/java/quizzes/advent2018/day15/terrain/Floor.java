package io.datura.java.quizzes.advent2018.day15.terrain;

public class Floor extends Terrain {
	public static final Floor FLOOR = new Floor();

	@Override
	public char getOutputSymbol() {
		return '.';
	}

}
