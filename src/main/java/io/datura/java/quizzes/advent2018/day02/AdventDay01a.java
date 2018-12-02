package io.datura.java.quizzes.advent2018.day02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

import io.datura.java.quizzes.advent2018.day01.AdventDay01;

public class AdventDay01a {

	public static void main(String[] args) {
		try {
			int stableFrequency = findStableFrequency(getDataFromInputFile());
			String output = String.format("Found a stable frequency of %d", stableFrequency);
			System.out.println(output);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O error when processing input. Aborting.");
			System.exit(1);
		}
	}

	public static Integer findStableFrequency(Integer[] frequencies) {
		Integer curFreq = 0;
		Set<Integer> calcFreq = new HashSet<>();
		calcFreq.add(curFreq);

		Iterator<Integer> changeIter = Arrays.asList(frequencies).iterator();
		while (changeIter.hasNext()) {
			final Integer freqChange = changeIter.next();

			curFreq += freqChange;

			// we've already encountered this frequency,
			// so we can stop
			if (calcFreq.contains(curFreq))
				return curFreq;
			else
				calcFreq.add(curFreq);

			// we want to iterate endlessly, so once
			// the iterator's been exhausted, we'll reinitialize
			// it before doing the evaluation
			if (!changeIter.hasNext())
				changeIter = Arrays.asList(frequencies).iterator();
		}

		// this is essentially dead code
		return 0;
	}

	private static Integer[] getDataFromInputFile() throws IOException {
		try {
			// the input file's stored relative to the class,
			// so retrieve it w/ the classloader
			ClassLoader classLoader = AdventDay01.class.getClassLoader();
			Path p = Paths.get(classLoader.getResource("values-manual.txt").toURI());
			// Files.lines stealthily opens (and keeps open) a handle, so
			// we need to ensure that the stream it produces gets closed
			try (Stream<String> lines = Files.lines(p)) {
				return lines.map(Integer::parseInt).toArray(Integer[]::new);
			}
		} catch (URISyntaxException urie) {
			throw new IOException("Unable to load input files, check file layout.");
		}
	}
}
