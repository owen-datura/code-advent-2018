package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day10.NavPoint;

public class AdventDay10Test {

	@Test
	public void initNavPoint() {
		int x = 9;
		int y = 1;
		int dx = 0;
		int dy = 2;

		NavPoint p = new NavPoint(x, y, dx, dy);

		// the origin should reflect the values provided
		// in the constructor
		assertEquals(9, p.getOrigX());
		assertEquals(1, p.getOrigY());
		// until we advance the state, the current
		// should equal the original values
		assertEquals(9, p.getPosX());
		assertEquals(1, p.getPosY());
	}

	@Test
	public void testStateAdvance() {
		int x = 9;
		int y = 1;
		int dx = 0;
		int dy = 2;

		NavPoint p = new NavPoint(x, y, dx, dy);

		// advance the state
		p.tick();

		// x is unchanged
		assertEquals(9, p.getPosX());
		// y + 2 = 3
		assertEquals(3, p.getPosY());
	}

	@Test
	public void testPageExample() {
		int x = 3;
		int y = 9;
		int dx = 1;
		int dy = -2;

		NavPoint p = new NavPoint(x, y, dx, dy);

		// three seconds
		p.tick();
		p.tick();
		p.tick();

		assertEquals(6, p.getPosX());
		assertEquals(3, p.getPosY());
	}

	@Test
	public void testValueParse() {
		String[] examples = getTestInput();

		// Test 1
		{
			String input = examples[0];
			NavPoint np = NavPoint.parseNavPoint(input);

			assertEquals(-42750, np.getOrigX());
			assertEquals(-53498, np.getOrigY());

			assertEquals(4, np.getDeltaX());
			assertEquals(5, np.getDeltaY());
		}

		// Test 2
		{
			String input = examples[1];
			NavPoint np = NavPoint.parseNavPoint(input);

			assertEquals(-10569, np.getOrigX());
			assertEquals(-21315, np.getOrigY());

			assertEquals(1, np.getDeltaX());
			assertEquals(2, np.getDeltaY());
		}
		
		// Test 3
		{
			String input = examples[2];
			NavPoint np = NavPoint.parseNavPoint(input);

			assertEquals(-21334, np.getOrigX());
			assertEquals(53779, np.getOrigY());

			assertEquals(2, np.getDeltaX());
			assertEquals(-5, np.getDeltaY());
		}
		
		// Test 4
		{
			String input = examples[3];
			NavPoint np = NavPoint.parseNavPoint(input);

			assertEquals(43055, np.getOrigX());
			assertEquals(-10586, np.getOrigY());

			assertEquals(-4, np.getDeltaX());
			assertEquals(1, np.getDeltaY());
		}
	}

	private String[] getTestInput() {
		String[] input = new String[4];
		input[0] = "position=<-42750, -53498> velocity=< 4,  5>";
		input[1] = "position=<-10569, -21315> velocity=< 1,  2>";
		input[2] = "position=<-21334,  53779> velocity=< 2, -5>";
		input[3] = "position=< 43055, -10586> velocity=<-4,  1>";
		return input;
	}
}
