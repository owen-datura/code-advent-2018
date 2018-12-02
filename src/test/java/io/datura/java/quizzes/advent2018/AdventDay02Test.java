package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day02.AdventDay02;

public class AdventDay02Test {
	@Test
	public void testIsNotEmpty() {
		String testString = "somecontents here are some spaces hello";
		assertFalse(AdventDay02.isEmpty(testString));
	}

	@Test
	public void testIsEmpty() {
		String testString = "";
		assertTrue(AdventDay02.isEmpty(testString));
	}

	@Test
	public void testNull() {
		String testString = null;
		assertTrue(AdventDay02.isEmpty(testString));
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
}
