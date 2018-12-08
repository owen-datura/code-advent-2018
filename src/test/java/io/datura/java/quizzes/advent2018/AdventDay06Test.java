package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day06.Destination;
import io.datura.java.quizzes.advent2018.day06.DestinationMatrix;

public class AdventDay06Test {

	private static List<Destination> getExampleDestinations() {
		List<Destination> destinations = new ArrayList<>();
		destinations.add(new Destination(1, 1, "A"));
		destinations.add(new Destination(1, 6, "B"));
		destinations.add(new Destination(8, 3, "C"));
		destinations.add(new Destination(3, 4, "D"));
		destinations.add(new Destination(5, 5, "E"));
		destinations.add(new Destination(8, 9, "F"));
		return destinations;
	}

	@Test
	public void createExamplePlot() {
		DestinationMatrix matrix = DestinationMatrix.create(getExampleDestinations());
		int maxCol = matrix.getMaxColumn();
		matrix.printMatrix();
		assertEquals(8, maxCol);
	}
}
