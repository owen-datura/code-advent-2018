package io.datura.java.quizzes.advent2018;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day06.Destination;

public class AdventDay06Test {

	private static Collection<Destination> getExampleDestinations() {
		Collection<Destination> destinations = new ArrayList<>();
		destinations.add(new Destination(1, 1));
		destinations.add(new Destination(1, 6));
		destinations.add(new Destination(8, 3));
		destinations.add(new Destination(3, 4));
		destinations.add(new Destination(5, 5));
		destinations.add(new Destination(8, 9));
		return destinations;
	}

	@Test
	public void createExamplePlot() {
		
	}

}
