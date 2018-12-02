package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day01.AdventDay01;

public class AdventDay01Test {
	
	@Test
	public void testInitialExample() {
		String[] initialExample = { "+1", "-2","+3","+1"};
		int sum = AdventDay01.sumInputStream(Arrays.stream(initialExample));
		assertEquals(3, sum);
	}
	
	@Test
	public void testExampleOne() {
		String[] exampleOne = { "+1", "+1", "+1" };
		int sum = AdventDay01.sumInputStream(Arrays.stream(exampleOne));
		assertEquals(3, sum);
	}
	
	@Test
	public void testExampleTwo() {
		String[] exampleTwo = { "+1", "+1", "-2" };
		int sum = AdventDay01.sumInputStream(Arrays.stream(exampleTwo));
		assertEquals(0, sum);
	}
	
	@Test
	public void testExampleThree() {
		String[] exampleThree = { "-1", "-2", "-3" };
		int sum = AdventDay01.sumInputStream(Arrays.stream(exampleThree));
		assertEquals(-6, sum);
	}
}