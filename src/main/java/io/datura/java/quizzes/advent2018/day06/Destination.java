package io.datura.java.quizzes.advent2018.day06;

public class Destination {
	private final int x;
	private final int y;
	private final String identifier;

	public Destination(int x, int y, String identifier) {
		this.x = x;
		this.y = y;
		this.identifier = identifier;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getIdentifier() {
		return identifier;
	}
}
