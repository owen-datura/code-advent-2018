package io.datura.java.quizzes.advent2018.day02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.datura.java.quizzes.advent2018.day01.AdventDay01;

public class AdventDay02 {
	private static int ASCII_NUM_LOWERCASE = 26;
	private static int ASCII_START_VALUE = 97;

	public static void main(String[] args) {
		try {
			Collection<String> boxCodes = getDataFromInputFile();

			Collection<SimpleImmutableEntry<Boolean, Boolean>> results = new ArrayList<>();
			for (String code : boxCodes) {
				results.add(hasDubsTrips(getCharacterMap(code)));
			}

			SimpleImmutableEntry<Integer, Integer> counts = getDubsTripsTotal(results);
			Integer doubles = counts.getKey();
			Integer triples = counts.getValue();
			String countsOutput = String.format("Found %d doubles and %d triples.", doubles, triples);
			System.out.println(countsOutput);

			Integer result = doubles * triples;
			String totalOutput = String.format("Calculated result of %d.", result);
			System.out.println(totalOutput);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O error when processing input. Aborting.");
			System.exit(1);
		}
	}

	public static SimpleImmutableEntry<Integer, Integer> getDubsTripsTotal(
			Collection<SimpleImmutableEntry<Boolean, Boolean>> l) {
		int doubleCount = 0;
		int tripleCount = 0;

		for (SimpleImmutableEntry<Boolean, Boolean> lv : l) {
			if (lv.getKey())
				doubleCount++;

			if (lv.getValue())
				tripleCount++;
		}

		// the requirements don't specify what happens if the input has zero of either
		// number

		return new SimpleImmutableEntry<Integer, Integer>(doubleCount, tripleCount);
	}

	// using this entry class as an ersatz Pair to avoid importing Commons
	public static SimpleImmutableEntry<Boolean, Boolean> hasDubsTrips(Map<Character, Integer> input) {
		Boolean hasDoubles = Boolean.FALSE;
		Boolean hasTriples = Boolean.FALSE;

		for (Integer v : input.values()) {
			if (!hasDoubles && Integer.valueOf(2).equals(v))
				hasDoubles = Boolean.TRUE;
			else if (!hasTriples && Integer.valueOf(3).equals(v))
				hasTriples = Boolean.TRUE;
		}

		return new SimpleImmutableEntry<Boolean, Boolean>(hasDoubles, hasTriples);
	}

	public static Map<Character, Integer> getCharacterMap(String boxCode) {
		if (isEmpty(boxCode))
			return Collections.emptyMap();

		Map<Character, Integer> characterMap = new HashMap<>();

		for (int i = 0, sz = boxCode.length(); i < sz; i++) {
			char c = boxCode.charAt(i);

			if (characterMap.containsKey(c)) {
				Integer cv = characterMap.get(c);
				characterMap.put(c, ++cv);
			} else {
				characterMap.put(c, 1);
			}
		}

		return characterMap;
	}

	public static boolean isEmpty(String input) {
		return input == null || input.isBlank();
	}

	@SuppressWarnings("unused")
	private static int[] countOccurrence(String vals) {
		int[] counts = new int[ASCII_NUM_LOWERCASE];
		for (char c : vals.toCharArray()) {
			int p = (c - 0) - ASCII_START_VALUE;
			counts[p]++;
		}
		return counts;
	}

	public static String printCharCountArray(int[] counts) {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < ASCII_NUM_LOWERCASE; i++) {
			char c = (char) (ASCII_START_VALUE + i);
			output.append(c);
			output.append(":\t");
			output.append(counts[i]);
			output.append("\n");
		}

		return output.toString();
	}

	private static Collection<String> getDataFromInputFile() throws IOException {
		try {
			// the input file's stored relative to the class,
			// so retrieve it w/ the classloader
			ClassLoader classLoader = AdventDay01.class.getClassLoader();
			Path p = Paths.get(classLoader.getResource("boxcodes.txt").toURI());
			// Files.lines stealthily opens (and keeps open) a handle, so
			// we need to ensure that the stream it produces gets closed
			return Files.readAllLines(p);
		} catch (URISyntaxException urie) {
			throw new IOException("Unable to load input files, check file layout.");
		}
	}
}
