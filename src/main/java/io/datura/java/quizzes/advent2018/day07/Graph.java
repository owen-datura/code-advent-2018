package io.datura.java.quizzes.advent2018.day07;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Graph {
	private Map<Character, Vertex> adj;

	private static final String INPUT_PATTERN = "Step ([A-Z]) must be finished before step ([A-Z]) can begin.";
	private static final Pattern INPUT_REGEX = Pattern.compile(INPUT_PATTERN);

	public Graph() {
		adj = new HashMap<>();
	}

	public static void main(String[] args) {
		Map<Character, Vertex> myGraph = new HashMap<Character, Vertex>();

		// 'Step C must be finished before step A can begin.'
		Vertex vc = new Vertex('C');

		Vertex va = new Vertex('A');
		vc.setNeighbor(va);

		myGraph.put('C', vc);
		myGraph.put('A', va);
	}

	public void addToGraph(Character precedes, Character target) {
		if (!Character.isAlphabetic(precedes) || !Character.isAlphabetic(target)) {
			System.err.println(String.format(
					"Attempting to add an invalid character to the map. Values were %s and %s.", precedes, target));
			return;
		}

		Vertex p = new Vertex(precedes);
		Vertex t = new Vertex(target);
		t.setNeighbor(p);

		adj.put(precedes, p);
		adj.put(target, t);
	}

	public void calcInDegrees() {
		
	}
}
