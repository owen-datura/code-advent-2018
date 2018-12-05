package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import io.datura.java.quizzes.advent2018.day04.AdventDay04;
import io.datura.java.quizzes.advent2018.day04.GuardIntelDateComparator;
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

		Collections.sort(events, new GuardIntelDateComparator());

		LocalDateTime dateTime = events.get(0).getLeft();
		assertEquals(1518, dateTime.getYear());
		assertEquals(10, dateTime.getMonthValue());
		assertEquals(31, dateTime.getDayOfMonth());
	}

	@Test
	public void testGetGuardId() {
		String activity = "[1518-03-28 23:59] Guard #1777 begins shift";
		int guardId = AdventDay04.getCurrentGuardFromActivity(activity);
		assertEquals(1777, guardId);
	}

	@Test
	public void testGuardSleepTime() {
		LocalDateTime fellAsleep = LocalDateTime.of(1518, 11, 1, 0, 5);
		LocalDateTime awoke = LocalDateTime.of(1518, 11, 1, 0, 25);
		long minsAsleep = AdventDay04.getTimeAsleep(fellAsleep, awoke);
		assertEquals(20l, minsAsleep);
	}

	@Test
	public void testGuardSleepTime2() {
		LocalDateTime fellAsleep = LocalDateTime.of(1518, 03, 27, 0, 16);
		LocalDateTime awoke = LocalDateTime.of(1518, 03, 27, 0, 33);
		long minsAsleep = AdventDay04.getTimeAsleep(fellAsleep, awoke);
		assertEquals(17l, minsAsleep);
	}

	@Test
	public void testSleepTimeCalculation() {
		String[] activities = new String[] { "[1518-03-27 00:03] Guard #2251 begins shift",
				"[1518-03-27 00:11] falls asleep", "[1518-03-27 00:57] wakes up",
				"[1518-03-27 23:58] Guard #3319 begins shift", "[1518-03-28 00:16] falls asleep",
				"[1518-03-28 00:33] wakes up", "[1518-03-28 00:53] falls asleep" };

		List<String> events = Arrays.asList(activities);
		Map<Integer, Long> sleepTimes = AdventDay04.evalGuardSleepTimes(events);

		// should consist of two guards
		assertEquals(2, sleepTimes.keySet().size());
		// guard 2251
		assertTrue(sleepTimes.containsKey(Integer.valueOf(2251)));
		// guard 2251 slept for 46 minutes
		assertEquals(Long.valueOf(46l), sleepTimes.get(2251));
		// guard 3319
		assertTrue(sleepTimes.containsKey(Integer.valueOf(3319)));
		// guard 3319 slept for 17 minutes
		assertEquals(Long.valueOf(17l), sleepTimes.get(3319));
	}

	@Test
	public void testSleepyGuard() {
		Map<Integer, Long> events = new HashMap<Integer, Long>();
		events.put(123, 10l);
		events.put(456, 5l);
		events.put(789, 50l);
		events.put(111, 45l);

		Integer guardId = AdventDay04.findSleepyGuard(events);
		assertEquals(Integer.valueOf(789), guardId);
	}
}
