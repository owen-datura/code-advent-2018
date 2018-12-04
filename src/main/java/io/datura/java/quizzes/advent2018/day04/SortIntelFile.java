package io.datura.java.quizzes.advent2018.day04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SortIntelFile {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	public static void main(String[] args) {

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
