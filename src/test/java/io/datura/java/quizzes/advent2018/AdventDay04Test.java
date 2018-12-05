package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import io.datura.java.quizzes.advent2018.day04.AdventDay04;
import io.datura.java.quizzes.advent2018.day04.GuardActivity;
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
		GuardActivity record = new GuardActivity(123);
		record.addSleepTime(fellAsleep, awoke);
		int minsAsleep = record.getSumTimeAsleep();
		assertEquals(20l, minsAsleep);
	}

	@Test
	public void testGuardSleepTime2() {
		LocalDateTime fellAsleep = LocalDateTime.of(1518, 03, 27, 0, 16);
		LocalDateTime awoke = LocalDateTime.of(1518, 03, 27, 0, 33);
		GuardActivity record = new GuardActivity(123);
		record.addSleepTime(fellAsleep, awoke);
		int minsAsleep = record.getSumTimeAsleep();
		assertEquals(17l, minsAsleep);
	}

	@Test
	public void testSleepTimeCalculation() {
		String[] activities = new String[] { "[1518-03-27 00:03] Guard #2251 begins shift",
				"[1518-03-27 00:11] falls asleep", "[1518-03-27 00:57] wakes up",
				"[1518-03-27 23:58] Guard #3319 begins shift", "[1518-03-28 00:16] falls asleep",
				"[1518-03-28 00:33] wakes up"};

		List<String> events = Arrays.asList(activities);
		Map<Integer, GuardActivity> sleepTimes = AdventDay04.evalGuardSleepTimes(events);

		// should consist of two guards
		assertEquals(2, sleepTimes.keySet().size());
		// guard 2251
		assertTrue(sleepTimes.containsKey(Integer.valueOf(2251)));
		// guard 2251 slept for 46 minutes
		assertEquals(46, sleepTimes.get(2251).getSumTimeAsleep());
		// guard 3319
		assertTrue(sleepTimes.containsKey(Integer.valueOf(3319)));
		// guard 3319 slept for 17 minutes
		assertEquals(17, sleepTimes.get(3319).getSumTimeAsleep());
	}

	@Test
	public void testOverlappingStartTime() {
		LocalDateTime before = LocalDateTime.of(2018, 12, 03, 23, 55);
		LocalDateTime after = LocalDateTime.of(2018, 12, 04, 00, 15);
		LocalDateTime adjusted = GuardActivity.getAdjustedStartTime(before, after);

		assertEquals(4, adjusted.getDayOfMonth());
		assertEquals(0, adjusted.getHour());
		assertEquals(0, adjusted.getMinute());
	}

	@Test
	public void testNonOverlappingStartTime() {
		LocalDateTime before = LocalDateTime.of(2018, 12, 04, 01, 05);
		LocalDateTime after = LocalDateTime.of(2018, 12, 04, 02, 17);
		LocalDateTime adjusted = GuardActivity.getAdjustedStartTime(before, after);

		assertEquals(4, adjusted.getDayOfMonth());
		assertEquals(1, adjusted.getHour());
		assertEquals(5, adjusted.getMinute());
	}

	@Test
	public void testSleepTimeBitSet() {
		LocalDateTime sleep = LocalDateTime.of(2018, 11, 01, 00, 05);
		LocalDateTime woke = LocalDateTime.of(2018, 11, 01, 00, 25);
		BitSet sleepTime = GuardActivity.createSleepTimeBitSet(sleep, woke);
		assertEquals(20, sleepTime.cardinality());
	}

	@Test
	public void testGuardSleepTimeMapping() {
		GuardActivity guardRecord = new GuardActivity(123);

		LocalDateTime sleep = LocalDateTime.of(2018, 11, 01, 00, 05);
		LocalDateTime woke = LocalDateTime.of(2018, 11, 01, 00, 25);
		int mins = guardRecord.addSleepTime(sleep, woke);

		// guardRecord.printSleepTimes();

		assertEquals(20, mins);
	}

	@Test
	public void testOverlappingGuardSleepTimeMapping() {
		GuardActivity guardRecord = new GuardActivity(123);

		{
			LocalDateTime sleep = LocalDateTime.of(2018, 11, 01, 00, 05);
			LocalDateTime woke = LocalDateTime.of(2018, 11, 01, 00, 25);
			guardRecord.addSleepTime(sleep, woke);
		}

		int mins = 0;
		{
			LocalDateTime sleep2 = LocalDateTime.of(2018, 11, 01, 00, 30);
			LocalDateTime woke2 = LocalDateTime.of(2018, 11, 01, 00, 55);
			mins = guardRecord.addSleepTime(sleep2, woke2);
		}

		// guardRecord.printSleepTimes();

		assertEquals(45, mins);
	}

	@Test
	public void testMultiDaySleepMapping() {
		GuardActivity guardRecord = new GuardActivity(10);

		{
			LocalDateTime sleep = LocalDateTime.of(2018, 11, 01, 00, 05);
			LocalDateTime woke = LocalDateTime.of(2018, 11, 01, 00, 25);
			guardRecord.addSleepTime(sleep, woke);
		}

		{
			LocalDateTime sleep2 = LocalDateTime.of(2018, 11, 01, 00, 30);
			LocalDateTime woke2 = LocalDateTime.of(2018, 11, 01, 00, 55);
			guardRecord.addSleepTime(sleep2, woke2);
		}

		{
			LocalDateTime sleep3 = LocalDateTime.of(2018, 11, 03, 00, 24);
			LocalDateTime woke3 = LocalDateTime.of(2018, 11, 03, 00, 29);
			guardRecord.addSleepTime(sleep3, woke3);
		}

		// guardRecord.printSleepTimes();

		int totalTimeAsleep = guardRecord.getSumTimeAsleep();

		assertEquals(50, totalTimeAsleep);
	}
}
