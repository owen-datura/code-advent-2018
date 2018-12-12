package io.datura.java.quizzes.advent2018.day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdventDay10 {
	private static int ITERATIONS = 3;

	public static void main(String[] args) {
		try {
			Collection<NavPoint> points = parseInputFile("signal-small.txt");
			NavPlot plot = new NavPlot();

			for (NavPoint point : points) {
				plot.addPlotPoint(point);
			}

			Gson generator = getJsonConverter();
			for (int i = 0; i < ITERATIONS; i++) {
				Path tmp = Files.createTempFile("signal-out_" + i, ".json");
				try (BufferedWriter writer = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
					writer.write(generator.toJson(plot));
				}
				Path cwd = Path.of(getFilenameByIteration(i));
				Files.copy(tmp, cwd);
				
				plot.tick();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	private static String getFilenameByIteration(int i) {
		return String.format("signal-out_%d.json", i);
	}

	private static Gson getJsonConverter() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(NavPlot.class, new NavPlotTypeAdapter());
		builder.setPrettyPrinting();
		return builder.create();
	}

	private static Collection<NavPoint> parseInputFile(String fileName) throws IOException {
		try {
			ClassLoader cl = AdventDay10.class.getClassLoader();
			Path p = Paths.get(cl.getResource(fileName).toURI());
			try (BufferedReader reader = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
				String curLine = null;
				NavPoint point = null;
				Collection<NavPoint> points = new ArrayList<>();
				while ((curLine = reader.readLine()) != null) {
					point = NavPoint.parseNavPoint(curLine);
					if (point != null)
						points.add(point);
				}

				return points;
			}
		} catch (URISyntaxException urie) {
			throw new IOException("Encountered an I/O exception while loading input file.");
		}
	}
}
