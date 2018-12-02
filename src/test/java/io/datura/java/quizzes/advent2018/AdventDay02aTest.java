package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day02.AdventDay02a;
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
		 * *xx**x djrida dbdide
		 */

		BitSet bs = BoxCode.calculateHammingDistanceBitSet(bc1, bc2);
		int distance = BoxCode.getNumHighBits(bs);
		assertEquals(3, distance);
	}

	@Test
	public void testDistanceComparision() {
		List<BoxCode> codes = new ArrayList<>();

		BoxCode bc1 = new BoxCode("abcdef");
		bc1.setClosestDistance(3);
		codes.add(bc1);

		BoxCode bc2 = new BoxCode("fghuij");
		bc2.setClosestDistance(5);
		codes.add(bc2);

		BoxCode bc3 = new BoxCode("zzfghr");
		bc3.setClosestDistance(2);
		codes.add(bc3);

		BoxCode closestMatch = BoxCode.getClosestMatch(codes);
		assertEquals(bc2, closestMatch);
	}

	@Test
	public void testSimpleEvaluation() {
		List<String> codes = new ArrayList<>();
		// initial
		codes.add("abcdef");
		// similar in 3 positions
		codes.add("abcfgh");
		// similar in 1 position
		codes.add("ahijkl");
		// similar in 5
		codes.add("abcdeg");

		List<BoxCode> bcl = AdventDay02a.stringToObj(codes);
		for (BoxCode code : bcl) {
			for (BoxCode evalCode : bcl) {
				// intentional equality check, don't evaluate
				// against ourselves
				if (code != evalCode)
					code.evaluate(evalCode);
			}
		}

		assertTrue(true);
	}

	@Test
	public void testExampleData() {
		List<String> l = new ArrayList<>();
		l.add("abcde");
		l.add("fghij");
		l.add("klmno");
		l.add("pqrst");
		l.add("fguij");
		l.add("axcye");
		l.add("wvxyz");

		List<BoxCode> codeList = AdventDay02a.stringToObj(l);
		for (BoxCode code : codeList) {
			for (BoxCode evalCode : codeList) {
				// intentional equality check, don't evaluate
				// against ourselves
				if (code != evalCode)
					code.evaluate(evalCode);
			}
		}

		BoxCode closest = BoxCode.getClosestMatch(codeList);
		int distance = closest.getClosestDistance();
		String codeA = closest.getCode();
		String codeB = closest.getClosestMatchingCode();

		String output = String.format("Closest match found with Hamming distance of %d. Values were [%s] and [%s].",
				distance, codeA, codeB);
		System.out.println(output);

		assertTrue(codeA.equalsIgnoreCase("fghij") || codeA.equals("fguij"));
		assertTrue(codeB.equalsIgnoreCase("fghij") || codeB.equals("fguij"));
	}
}
