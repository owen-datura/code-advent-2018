package io.datura.java.quizzes.advent2018.day15.terrain;

public class Wall extends Terrain {
	public static final Wall WALL = new Wall();

	private Wall() {
	}

	@Override
	public char getOutputSymbol() {
		return '#';
	}

	@Override
	public boolean canBeMoved() {
		return false;
	}
}
