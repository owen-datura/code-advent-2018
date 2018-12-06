package io.datura.java.quizzes.advent2018.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdventDay05a {
	private static final Set<String> reactivePairs = createReactivePairs();

	private static final int ASCII_LINE_FEED = 10;
	private static final int ASCII_FORM_FEED = 12;
	private static final int ASCII_CR = 13;

	public static void main(String[] args) {
		try {
			// load the input once, we'll filter a copy for each trial
			Deque<Character> polymer = loadInputFile();
			Map<String, Integer> filterResults = new HashMap<String, Integer>();
			for (String pair : reactivePairs) {
				Deque<Character> filtered = filterPolymerUnit(polymer, pair);
				int result = processPolymerString(filtered);
				filterResults.put(pair, result);
			}
			// the output's doubled because both pair orders are
			// present, but the result will be the same for both
			showPairStats(filterResults);
			System.out.println("##");
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O error when processing file. Aborting.");
			System.exit(1);
		}
	}

	public static void showPairStats(Map<String, Integer> stats) {
		StringBuilder output = new StringBuilder();
		for (Entry<String, Integer> e : stats.entrySet()) {
			output.append(e.getKey());
			output.append(":\t");
			output.append(e.getValue());
			output.append("\n");
		}
		System.out.println(output);
	}

	public static Deque<Character> filterPolymerUnit(Deque<Character> oq, String unitPair) {
		if (oq == null || oq.size() == 0)
			return oq;

		char lp = unitPair.charAt(0);
		char rp = unitPair.charAt(1);

		Predicate<Character> unitPredicate = c -> (c != lp && c != rp);
		return oq.stream().filter(unitPredicate).collect(Collectors.toCollection(ArrayDeque::new));
	}

	private static Deque<Character> loadInputFile() throws IOException {
		try {
			ClassLoader cl = AdventDay05a.class.getClassLoader();
			Path inputFile = Paths.get(cl.getResource("polymer.txt").toURI());
			// this exercise gives us a file with a gigantic single line
			// that we'll end up needing to convert into individual characters
			// anyway, so instead of reading all lines do it piecemeal
			Deque<Character> out = new ArrayDeque<>();
			try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
				int c;
				while ((c = reader.read()) != -1) {
					// account for linefeeds
					if (ASCII_CR == c || ASCII_FORM_FEED == c || ASCII_LINE_FEED == c)
						continue;

					out.add((char) c);
				}
			}
			return out;
		} catch (URISyntaxException urie) {
			throw new IOException("Encountered an I/O error when loading input file.");
		}
	}

	public static int processPolymerString(Deque<Character> in) {
		Deque<Character> out = new ArrayDeque<>();
		for (Character c : in) {
			if (out.isEmpty())
				out.push(c);
			else {
				Character prev = out.peek();

				char[] p = new char[] { c, prev };
				if (isReactivePair(p))
					out.pop();
				else
					out.push(c);
			}
		}

		return out.size();
	}

	private static boolean isReactivePair(char[] chars) {
		return reactivePairs.contains(new String(chars));
	}

	public static Set<String> createReactivePairs() {
		// the 'reactive' polymers are defined as being
		// an ASCII character of the same letter
		// but in the opposite case. there's plenty
		// of tricks one can use for this, but the
		// obvious (naive?) one involves just pre-computing
		// the combinations
		Set<String> polymerPairs = new HashSet<>(64);
		polymerPairs.add("Aa");
		polymerPairs.add("aA");
		polymerPairs.add("Bb");
		polymerPairs.add("bB");
		polymerPairs.add("Cc");
		polymerPairs.add("cC");
		polymerPairs.add("Dd");
		polymerPairs.add("dD");
		polymerPairs.add("Ee");
		polymerPairs.add("eE");
		polymerPairs.add("Ff");
		polymerPairs.add("fF");
		polymerPairs.add("Gg");
		polymerPairs.add("gG");
		polymerPairs.add("Hh");
		polymerPairs.add("hH");
		polymerPairs.add("Ii");
		polymerPairs.add("iI");
		polymerPairs.add("Jj");
		polymerPairs.add("jJ");
		polymerPairs.add("Kk");
		polymerPairs.add("kK");
		polymerPairs.add("Ll");
		polymerPairs.add("lL");
		polymerPairs.add("Mm");
		polymerPairs.add("mM");
		polymerPairs.add("Nn");
		polymerPairs.add("nN");
		polymerPairs.add("Oo");
		polymerPairs.add("oO");
		polymerPairs.add("Pp");
		polymerPairs.add("pP");
		polymerPairs.add("Qq");
		polymerPairs.add("qQ");
		polymerPairs.add("Rr");
		polymerPairs.add("rR");
		polymerPairs.add("Ss");
		polymerPairs.add("sS");
		polymerPairs.add("Tt");
		polymerPairs.add("tT");
		polymerPairs.add("Uu");
		polymerPairs.add("uU");
		polymerPairs.add("Vv");
		polymerPairs.add("vV");
		polymerPairs.add("Ww");
		polymerPairs.add("wW");
		polymerPairs.add("Xx");
		polymerPairs.add("xX");
		polymerPairs.add("Yy");
		polymerPairs.add("yY");
		polymerPairs.add("Zz");
		polymerPairs.add("zZ");
		return Collections.unmodifiableSet(polymerPairs);
	}
}
