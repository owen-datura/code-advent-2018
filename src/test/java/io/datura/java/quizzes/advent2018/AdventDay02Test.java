package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day02.AdventDay02;
import io.datura.java.quizzes.advent2018.util.AdventUtils;

public class AdventDay02Test {
	@Test
	public void testIsNotEmpty() {
		String testString = "somecontents here are some spaces hello";
		assertFalse(AdventUtils.isEmpty(testString));
	}

	@Test
	public void testIsEmpty() {
		String testString = "";
		assertTrue(AdventUtils.isEmpty(testString));
	}

	@Test
	public void testNull() {
		String testString = null;
		assertTrue(AdventUtils.isEmpty(testString));
	}

	@Test
	public void testDoubles() {
		Map<Character, Integer> counts = new HashMap<>();
		counts.put('a', 1);
		counts.put('b', 2);
		counts.put('g', 3);
		counts.put('h', 2);
		SimpleImmutableEntry<Boolean, Boolean> checkResult = AdventDay02.hasDubsTrips(counts);
		assertTrue(checkResult.getKey());
	}

	@Test
	public void testNoDoubles() {
		Map<Character, Integer> counts = new HashMap<>();
		counts.put('a', 1);
		counts.put('b', 3);
		counts.put('g', 5);
		counts.put('h', 8);
		counts.put('j', 4);
		SimpleImmutableEntry<Boolean, Boolean> checkResult = AdventDay02.hasDubsTrips(counts);
		assertFalse(checkResult.getKey());
	}

	@Test
	public void testTriples() {
		Map<Character, Integer> counts = new HashMap<>();
		counts.put('a', 1);
		counts.put('b', 2);
		counts.put('g', 3);
		counts.put('h', 2);
		SimpleImmutableEntry<Boolean, Boolean> checkResult = AdventDay02.hasDubsTrips(counts);
		assertTrue(checkResult.getValue());
	}

	@Test
	public void testNoTriples() {
		Map<Character, Integer> counts = new HashMap<>();
		counts.put('a', 1);
		counts.put('b', 2);
		counts.put('g', 5);
		counts.put('h', 8);
		counts.put('j', 4);
		SimpleImmutableEntry<Boolean, Boolean> checkResult = AdventDay02.hasDubsTrips(counts);
		assertFalse(checkResult.getValue());
	}

	@Test
	public void testCount() {
		String[] boxCodes = { "abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab" };

		Collection<SimpleImmutableEntry<Boolean, Boolean>> results = new ArrayList<>();
		for (String code : boxCodes) {
			results.add(AdventDay02.hasDubsTrips(AdventDay02.getCharacterMap(code)));
		}

		SimpleImmutableEntry<Integer, Integer> counts = AdventDay02.getDubsTripsTotal(results);

		Integer doubles = counts.getKey();
		assertEquals(Integer.valueOf(4), doubles);
		Integer triples = counts.getValue();
		assertEquals(Integer.valueOf(3), triples);

		/*
		 * String output = String.format("Found %d doubles and %d triples.", doubles,
		 * triples); System.out.println(output);
		 */

		Integer result = doubles * triples;
		assertEquals(Integer.valueOf(12), result);
	}
}
