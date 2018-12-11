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

public class AdventDay10 {
	private static int ITERATIONS = 2_000;

	public static void main(String[] args) {
		try {
			Collection<NavPoint> points = parseInputFile("signals.txt");
			NavPlot plot = new NavPlot();

			System.out.println("Adding points...");

			// load the points
			for (NavPoint point : points) {
				plot.addPlotPoint(point);
			}

			Path tmp = Files.createTempFile("signal-", ".txt");
			try (BufferedWriter writer = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
				System.out.println("Iterating points...");

				for (int i = 0; i < ITERATIONS; i++) {
					String output = String.format("Iteration #%02d", i);
					writer.write(output);
					writer.write("\n");

					// get and print the current state of the points
					NavPoint[][] signalState = plot.createPlot();
					NavPlot.printPlot(signalState, writer);
					
					// advance the state by one tick
					plot.tick();
				}
			}

			Path cwd = Paths.get("./signal-output.txt");
			System.out.println(String.format("Creating output file %s.", cwd.toAbsolutePath().toString()));
			Files.move(tmp, cwd);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
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
