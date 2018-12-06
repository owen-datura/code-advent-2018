package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day05.AdventDay05;
import io.datura.java.quizzes.advent2018.day05.AdventDay05a;

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

	@Test
	public void testPredicateBehavior() {
		Deque<Character> dq = new ArrayDeque<>();
		dq.add('A');
		dq.add('B');
		dq.add('C');
		dq.add('D');
		dq.add('E');

		String toFilter = "BC";
		Deque<Character> output = AdventDay05a.filterPolymerUnit(dq, toFilter);

		assertEquals(3, output.size());
		char tmp = output.pop();
		assertEquals('A', tmp);
		tmp = output.pop();
		assertEquals('D', tmp);
		tmp = output.pop();
		assertEquals('E', tmp);

		assertTrue(output.isEmpty());
	}
}
