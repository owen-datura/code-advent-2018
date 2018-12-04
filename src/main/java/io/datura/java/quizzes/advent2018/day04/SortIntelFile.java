package io.datura.java.quizzes.advent2018.day04;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class SortIntelFile {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static void main(String[] args) {
		try {
			List<Pair<LocalDateTime, String>> intelList = readAndConvertIntel();

			Collections.sort(intelList, new GuardIntelComparator());

			Path sortedFileLoc = writeGuardIntel(intelList);

			System.out.println(String.format("Wrote output file to: %s", sortedFileLoc.toAbsolutePath()));
		} catch (IOException ioe) {
			System.err.println("Encountered a critical error when processing input. Aborting.");
			System.exit(1);
		}
	}

	private static Path writeGuardIntel(List<Pair<LocalDateTime, String>> intel) throws IOException {
		Path tmpFile = Files.createTempFile("gi-", ".txt");
		try (PrintWriter writer = new PrintWriter(tmpFile.toFile(), StandardCharsets.UTF_8)) {
			for (Pair<LocalDateTime, String> p : intel) {
				writer.write(p.getRight());
				writer.write("\n");
			}
		}

		Path sortedFile = Paths.get("intel-sorted.txt");
		Files.move(tmpFile, sortedFile);
		return sortedFile;
	}

	private static List<Pair<LocalDateTime, String>> readAndConvertIntel() throws IOException {
		try {
			ClassLoader cl = SortIntelFile.class.getClassLoader();
			Path input = Paths.get(cl.getResource("intel-unsorted.txt").toURI());
			try (Stream<String> inputStream = Files.lines(input)) {
				return inputStream.map(SortIntelFile::parseDate).collect(Collectors.toList());
			}
		} catch (URISyntaxException use) {
			throw new IOException("Error when retrieving unsorted data file.");
		}
	}

	public static Pair<LocalDateTime, String> parseDate(String input) {
		return new ImmutablePair<LocalDateTime, String>(parseInputDate(input), input);
	}

	public static LocalDateTime parseInputDate(String input) {
		// since the input is in a standard format, using a
		// regex here is kinda overkill. instead i'll
		// just extract the date portion and let the time lib
		// handle it
		String datePart = input.substring(1, input.indexOf(']'));
		return LocalDateTime.parse(datePart, DATE_FORMAT);
	}
}
