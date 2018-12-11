package io.datura.java.quizzes.advent2018.day10;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class PlotTest {
	private static final Pair<Integer, Integer> ORIGIN = new ImmutablePair<Integer, Integer>(0, 0);

	public static void main(String[] args) {
		Collection<NavPoint> points = new ArrayList<>();
		
		NavPoint p = new NavPoint(6,0,0,0);
		points.add(p);
		
		NavPoint p2 = new NavPoint(-6, 0, 0, 0);
		points.add(p2);
		
		NavPoint p3 = new NavPoint(0, 0, 0, 0);
		points.add(p3);
		
		Pair<Integer, Integer> dimensions = getPlotDim(points);
		int dimX = dimensions.getLeft();

		int ex = p.getCorrectedPosX(dimX);
		String output = String.format("Plot's X dimension extends %d units. The plotted point %d is at index %d.", dimX, p.getPosX(), ex);
		System.out.println(output);
		
		ex = p2.getCorrectedPosX(dimX);
		output = String.format("Plot's X dimension extends %d units. The plotted point %d is at index %d.", dimX, p2.getPosX(), ex);
		System.out.println(output);
	}

	public static Pair<Integer, Integer> getPlotDim(Collection<NavPoint> points) {
		if (points == null || points.size() == 0)
			return ORIGIN;

		int Xmin = 0;
		int Xmax = 0;
		int Ymin = 0;
		int Ymax = 0;

		for (NavPoint p : points) {
			Xmin = Math.min(p.getPosX(), Xmin);
			Xmax = Math.max(p.getPosX(), Xmax);

			Ymin = Math.min(p.getPosY(), Ymin);
			Ymax = Math.max(p.getPosY(), Ymax);
		}

		/*
		 * Represent -X/+X using an array (which doesn't accept a negative index). If we
		 * have -6 & +6, the distance between them is 13 (-6 to 0, 0 to +6) That's
		 * represented by max(posX) + abs(min(posX)) + 1
		 */
		int Xrange = Math.abs(Xmin) + Xmax + 1;
		int Yrange = Math.abs(Ymin) + Ymax + 1;

		// expanding the plot doesn't matter here, so make the range
		// easily divisible for when it comes time to adjust the point locations
		if (Xrange % 2 != 0)
			Xrange++;

		if (Yrange % 2 != 0)
			Yrange++;

		return new ImmutablePair<Integer, Integer>(Xrange, Yrange);
	}
}
