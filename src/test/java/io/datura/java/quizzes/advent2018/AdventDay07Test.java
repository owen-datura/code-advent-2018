package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import io.datura.java.quizzes.advent2018.day07.AdventDay07;
import io.datura.java.quizzes.advent2018.util.DiGraph;

public class AdventDay07Test {
	@Test
	public void testInputParse() {
		String input = "Step A must be finished before step B can begin.";
		Pair<Character, Character> values = AdventDay07.parseInputLine(input);
		assertEquals(Character.valueOf('A'), values.getLeft());
		assertEquals(Character.valueOf('B'), values.getRight());
	}

	@Test
	public void testPageExample() {
		Collection<Pair<Character, Character>> pairs = AdventDay07.toPairs(Arrays.asList(getDirectives()));
		DiGraph graph = new DiGraph();
		for (Pair<Character, Character> pair : pairs) {
			graph.addVertexPair(pair.getLeft(), pair.getRight());
		}
		List<Character> resolve = graph.resolve();
		String result = resolve.stream().map(c -> Character.toString(c)).collect(Collectors.joining(""));
		assertEquals("CABDFE", result);
	}

	private static String[] getDirectives() {
		String[] d = new String[7];
		d[0] = "Step C must be finished before step A can begin.";
		d[1] = "Step C must be finished before step F can begin.";
		d[2] = "Step A must be finished before step B can begin.";
		d[3] = "Step A must be finished before step D can begin.";
		d[4] = "Step B must be finished before step E can begin.";
		d[5] = "Step D must be finished before step E can begin.";
		d[6] = "Step F must be finished before step E can begin.";
		return d;
	}
}
