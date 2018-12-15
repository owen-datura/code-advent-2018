package io.datura.java.quizzes.advent2018.day15.file;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import io.datura.java.quizzes.advent2018.day15.GameWorld;

public class GameFileReader {
	private static List<String> loadInput(String filename) throws IOException {
		try {
			ClassLoader cl = GameFileReader.class.getClassLoader();
			Path p = Paths.get(cl.getResource(filename).toURI());
			return Files.readAllLines(p, StandardCharsets.UTF_8);
		} catch (URISyntaxException urie) {
			throw new IOException("Encountered an I/O exception when loading game state from file.");
		}
	}
}
