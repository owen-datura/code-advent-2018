package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import io.datura.java.quizzes.advent2018.day04.GuardIntelComparator;
import io.datura.java.quizzes.advent2018.day04.SortIntelFile;

public class AdventDay04Test {

	@Test
	public void testDatePattern() {
		String exampleDate = "[1518-08-28 00:24] abbbfbgfgb";
		LocalDateTime ldt = SortIntelFile.parseInputDate(exampleDate);

		assertEquals(1518, ldt.getYear());
		assertEquals(8, ldt.getMonthValue());
		assertEquals(28, ldt.getDayOfMonth());
		assertEquals(0, ldt.getHour());
		assertEquals(24, ldt.getMinute());
	}

	@Test
	public void testComparator() {
		String[] testStrings = new String[] { "[1518-11-01 00:00] Guard #10 begins shift",
				"[1518-10-31 23:58] Guard #99 begins shift", "[1518-11-01 00:05] falls asleep", };

		List<Pair<LocalDateTime, String>> events = Arrays.asList(testStrings).stream().map(SortIntelFile::parseDate)
				.collect(Collectors.toList());

		Collections.sort(events, new GuardIntelComparator());

		LocalDateTime dateTime = events.get(0).getLeft();
		assertEquals(1518, dateTime.getYear());
		assertEquals(10, dateTime.getMonthValue());
		assertEquals(31, dateTime.getDayOfMonth());
	}
}
