package io.datura.java.quizzes.advent2018.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AdventDay08 {
	public static void main(String[] args) {
		try {
			String nodeList = loadNodeFile();
			Node n = LicenseReader.createNodeFromLicenseString(nodeList);
			
			int sum = LicenseReader.sumMetaData(n);
			String output = String.format("Step #1: Summed nodes, total was %d.", sum);
			System.out.println(output);
			
			int sum2 = LicenseReader.altSumMetaData(n);
			String output2 = String.format("Step #2: Alternate summed nodes, total was %d.", sum2);
			System.out.println(output2);
		} catch (IOException e) {
			System.exit(1);
		}
	}

	private static String loadNodeFile() throws IOException {
		try {
			ClassLoader cl = AdventDay08.class.getClassLoader();
			Path p = Paths.get(cl.getResource("nodes.txt").toURI());
			try (BufferedReader reader = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
				return reader.readLine();
			}
		} catch (URISyntaxException urie) {
			throw new IOException("Encountered an I/O exception when reading input file.");
		}
	}
}
