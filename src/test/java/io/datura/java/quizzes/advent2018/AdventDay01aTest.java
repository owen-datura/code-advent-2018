package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day02.AdventDay01a;

public class AdventDay01aTest {
	@Test
	public void testInitialExample() {
		Integer[] values = new Integer[] {1,-2,3,1};
		int result = AdventDay01a.findStableFrequency(values);
		assertEquals(2, result);
	}
	
	@Test
	public void testExampleOne() {
		Integer[] values = new Integer[] {1,-1};
		int result = AdventDay01a.findStableFrequency(values);
		assertEquals(0, result);
	}
	
	@Test
	public void testExampleTwo() {
		Integer[] values = new Integer[] {3,3,4,-2,-4};
		int result = AdventDay01a.findStableFrequency(values);
		assertEquals(10, result);
	}
	
	@Test
	public void testExampleThree() {
		Integer[] values = new Integer[] {-6,3,8,5,-6};
		int result = AdventDay01a.findStableFrequency(values);
		assertEquals(5, result);
	}
	
	@Test
	public void testExampleFour() {
		Integer[] values = new Integer[] {7,7,-2,-7,-4};
		int result = AdventDay01a.findStableFrequency(values);
		assertEquals(14, result);
	}
}
