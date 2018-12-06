package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day05.AdventDay05;

public class AdventDay05Test {
	
	@Test
	public void testSelfDestructingPair() {
		String polymer = "aA";
		int sz = AdventDay05.processPolymerString(polymer);
		assertEquals(0, sz);
	}
	
	@Test
	public void testChainReaction() {
		String polymer = "abBA";
		int sz = AdventDay05.processPolymerString(polymer);
		assertEquals(0, sz);
	}
	
	@Test
	public void testNoOp() {
		String polymer = "abAB";
		int sz = AdventDay05.processPolymerString(polymer);
		assertEquals(4, sz);
	}
	
	@Test
	public void testCaseNoOp() {
		String polymer = "aabAAB";
		int sz = AdventDay05.processPolymerString(polymer);
		assertEquals(6, sz);
	}
	
	@Test
	public void testLargeExample() {
		String polymer = "dabAcCaCBAcCcaDA";
		int sz = AdventDay05.processPolymerString(polymer);
		assertEquals(10, sz);
	}
	

}
