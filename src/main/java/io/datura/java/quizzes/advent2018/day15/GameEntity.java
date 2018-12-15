package io.datura.java.quizzes.advent2018.day15;

public interface GameEntity {
	public char getOutputSymbol();

	public default boolean canBeMoved() {
		return true;
	}

	public default boolean canBeOverwritten() {
		return false;
	}
}
