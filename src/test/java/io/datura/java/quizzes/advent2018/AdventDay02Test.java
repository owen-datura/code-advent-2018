package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}
