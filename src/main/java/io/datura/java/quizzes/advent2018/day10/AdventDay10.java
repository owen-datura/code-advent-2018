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
import com.google.gson.stream.JsonWriter;

public class AdventDay10 {
	private static int ITERATIONS = 20_000;

	public static void main(String[] args) {
		try {
			String fileName = "signals.txt";
			String outputFileName = createOutputFileName(fileName);
			Collection<NavPoint> points = parseInputFile(fileName);
			NavPlot plot = new NavPlot();

			for (NavPoint point : points) {
				plot.addPlotPoint(point);
			}

			System.out.println("Starting iterations...");

			Path tmp = Files.createTempFile("signal-out_", ".json");
			try (BufferedWriter writer = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8);
					JsonWriter jsonWriter = new JsonWriter(writer);) {
				Gson gson = getJsonConverter();
				jsonWriter.beginArray();
				for (int i = 0; i < ITERATIONS; i++) {
					gson.toJson(plot, NavPlot.class, jsonWriter);
					plot.tick();

					if (i % 100 == 0)
						System.out.println(String.format("Iterated %d.", i));
				}
				jsonWriter.endArray();

				System.out.print("Iteration complete.");
			}

			Path output = Paths.get(outputFileName);
			Files.move(tmp, output);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}

	public static String createOutputFileName(String inputName) {
		StringBuilder outputName = new StringBuilder();
		outputName.append(inputName.substring(0, inputName.lastIndexOf(".")));
		outputName.append("_output");
		outputName.append(".json");
		return outputName.toString();
	}

	private static Gson getJsonConverter() {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(NavPlot.class, new NavPlotTypeAdapter());
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
