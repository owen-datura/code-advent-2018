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
import java.util.HashSet;
import java.util.Set;

public class AdventDay05 {
	private static final Set<String> reactivePairs = createReactivePairs();

	public static void main(String[] args) {
		try {
			Deque<Character> polymer = loadInputFile();
			int chainSize = processPolymerString(polymer);
			// for viewing purposes the input file contains a newline,
			// so we need to subtract one to account for that
			chainSize--;
			String output = String.format("After processing, the polymer string contained %d values.", chainSize);
			System.out.println(output);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O error when processing file. Aborting.");
			System.exit(1);
		}
	}

	public static int processPolymerString(String input) {
		// dump the input string into a stack
		Deque<Character> in = new ArrayDeque<>();
		for (Character c : input.toCharArray()) {
			in.push(c);
		}
		return processPolymerString(in);
	}

	private static Deque<Character> loadInputFile() throws IOException {
		try {
			ClassLoader cl = AdventDay05.class.getClassLoader();
			Path inputFile = Paths.get(cl.getResource("polymer.txt").toURI());
			// this exercise gives us a file with a gigantic single line
			// that we'll end up needing to convert into individual characters
			// anyway, so instead of reading all lines do it piecemeal
			Deque<Character> out = new ArrayDeque<>();
			try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
				int c;
				while ((c = reader.read()) != -1) {
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
