package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

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
}
