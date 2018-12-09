package io.datura.java.quizzes.advent2018.day07;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import io.datura.java.quizzes.advent2018.util.DiGraph;

public class AdventDay07 {
	private static final String PATTERN = "Step ([A-Z]) must be finished before step ([A-Z]) can begin.";
	private static final Pattern inputPattern = Pattern.compile(PATTERN);

	public static void main(String[] args) {
		DiGraph graph = new DiGraph();
	}

	public static Collection<Pair<Character, Character>> toPairs(Collection<String> lines) {
		Collection<Pair<Character, Character>> pc = new ArrayList<>();
		Pair<Character, Character> p = null;
		for (String line : lines) {
			p = parseInputLine(line);
			if (p != null)
				pc.add(p);
		}
		return pc;
	}

	public static Pair<Character, Character> parseInputLine(String input) {
		Matcher m = inputPattern.matcher(input);
		if (!m.matches())
			return null;

		char g1 = m.group(1).charAt(0);
		char g2 = m.group(2).charAt(0);
		return new ImmutablePair<Character, Character>(g1, g2);
	}
}
