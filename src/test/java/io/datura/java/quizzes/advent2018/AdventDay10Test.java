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
}
