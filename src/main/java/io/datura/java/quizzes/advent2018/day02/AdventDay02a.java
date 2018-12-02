package io.datura.java.quizzes.advent2018.day02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.datura.java.quizzes.advent2018.day01.AdventDay01;

public class AdventDay02a {
	public static void main(String[] args) {
		try {
			List<BoxCode> codeList = stringToObj(getDataFromInputFile());
			for (BoxCode code : codeList) {
				for (BoxCode evalCode : codeList) {
					// intentional equality check, don't evaluate
					// against ourselves
					if (code != evalCode)
						code.evaluate(evalCode);
				}
			}
			
			BoxCode closest = BoxCode.getClosestMatch(codeList);
			int distance = closest.getClosestDistance();
			String codeA = closest.getCode();
			String codeB = closest.getClosestMatchingCode();

			String output = String.format("Closest match found with Hamming distance of %d. Values were [%s] and [%s].",
					distance, codeA, codeB);
			System.out.println(output);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O error when processing input file.");
			System.exit(1);
		}
	}

	public static List<BoxCode> stringToObj(Collection<String> codes) {
		List<BoxCode> bl = new ArrayList<>();
		for (String code : codes) {
			bl.add(new BoxCode(code));
		}
		return bl;
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
