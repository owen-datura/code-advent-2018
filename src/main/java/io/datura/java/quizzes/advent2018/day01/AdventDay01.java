package io.datura.java.quizzes.advent2018.day01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class AdventDay01 {
	public static void main(String[] args) {
		try {
			int sum = sumInputStream(loadInputFileStream());
			String output = String.format("Sum of alterations was %d.", sum);
			System.out.println(output);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O error when processing input. Aborting.");
			System.exit(1);
		}
	}

	public static Integer sumInputStream(Stream<String> inputStream) {
		// using try-with-resources here to ensure the stream gets closed
		try (inputStream) {
			// #parseInt provides a means to convert values with a
			// leading sign into the appropriate integer format,
			// so we'll evaluate each line in the file
			// and apply it
			return inputStream.map(Integer::parseInt).reduce(0, Integer::sum);
		}
	}

	private static Stream<String> loadInputFileStream() throws IOException {
		try {
			// the input file's stored relative to the class,
			// so retrieve it w/ the classloader
			ClassLoader classLoader = AdventDay01.class.getClassLoader();
			Path p = Paths.get(classLoader.getResource("values-manual.txt").toURI());
			// Files.lines stealthily opens (and keeps open) a handle, so
			// we need to ensure that the stream it produces gets closed
			return Files.lines(p);
		} catch (URISyntaxException urie) {
			throw new IOException("Unable to load input files, check file layout.");
		}
	}
}
