package io.datura.java.quizzes.advent2018.day06;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Destination {
	private final int x;
	private final int y;

	public Destination(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	private static List<List<Destination>> mapDestinations(Collection<Destination> destinations) {
		if (destinations == null || destinations.size() == 0)
			return Collections.emptyList();

		return Collections.emptyList();
	}
}
