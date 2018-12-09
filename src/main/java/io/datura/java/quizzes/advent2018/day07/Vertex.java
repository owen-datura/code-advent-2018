package io.datura.java.quizzes.advent2018.day07;

public class Vertex {
	private final char label;
	private Vertex neighbor;

	public Vertex(char label) {
		this.label = label;
	}

	public char getLabel() {
		return label;
	}

	public void setNeighbor(Vertex neighbor) {
		this.neighbor = neighbor;
	}
}
