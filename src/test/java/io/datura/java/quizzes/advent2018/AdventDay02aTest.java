package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.BitSet;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day02.BoxCode;

public class AdventDay02aTest {
	@Test
	public void testArrayResize() {
		char[] src = new char[] { 'a', 'c', 'd' };
		int expandedSize = 5;
		char[] dest = BoxCode.resizeArray(src, expandedSize);
		assertTrue(dest.length == expandedSize);
	}

	@Test
	public void testArrayCopyBehavior() {
		char[] src = new char[] { 'a', 'c', 'd' };
		int expandedSize = 5;
		char[] dest = BoxCode.resizeArray(src, expandedSize);

		char[] testEval = new char[5];
		testEval[0] = 'a';
		testEval[1] = 'c';
		testEval[2] = 'd';

		assertArrayEquals(testEval, dest);
	}

	@Test
	public void testHighBitCalculation() {
		BitSet bs = new BitSet(8);
		bs.set(1);
		bs.set(5);
		bs.set(7);

		int actual = BoxCode.getNumHighBits(bs);
		assertEquals(3, actual);
	}
	
	@Test
	public void testHammingDistance() {
		BoxCode bc1 = new BoxCode("djrida");
		BoxCode bc2 = new BoxCode("dbdide");
		
		/*
		 *    *xx**x
		 *    djrida
		 *    dbdide
		 */
		
		BitSet bs = BoxCode.calculateHammingDistanceBitSet(bc1, bc2);
		int distance = BoxCode.getNumHighBits(bs);
		assertEquals(3, distance);
	}
}
