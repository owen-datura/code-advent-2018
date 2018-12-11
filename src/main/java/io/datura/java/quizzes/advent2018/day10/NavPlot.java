package io.datura.java.quizzes.advent2018.day10;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class NavPlot {
	private Collection<NavPoint> points = new ArrayList<>();

	private static final Pair<Integer, Integer> ORIGIN = new ImmutablePair<Integer, Integer>(0, 0);

	public static void main(String[] args) {
		NavPlot plot = new NavPlot();

		NavPoint p = new NavPoint(6, 0, 0, 0);
		plot.addPlotPoint(p);
		NavPoint p2 = new NavPoint(-6, 0, 0, 0);
		plot.addPlotPoint(p2);
		NavPoint p3 = new NavPoint(0, 0, 0, 0);
		plot.addPlotPoint(p3);

		NavPoint[][] matrix = plot.createPlot();
		NavPlot.printPlot(matrix);
	}

	public void addPlotPoint(NavPoint p) {
		if (p == null)
			return;

		points.add(p);
	}

	public NavPoint[][] createPlot() {
		// establish the plot dimensions
		Pair<Integer, Integer> dim = getPlotDim(points);
		// construct the graph we'll use to plot the points
		NavPoint[][] plot = new NavPoint[dim.getRight()][dim.getLeft()];

		int dimX = dim.getLeft();
		int dimY = dim.getRight();

		// now evaluate the points we were given, placing them on the plot
		for (NavPoint point : points) {
			int cx = point.getCorrectedPosX(dimX);
			int cy = point.getCorrectedPosY(dimY);

			plot[cy][cx] = point;
		}

		return plot;
	}

	public static void printPlot(NavPoint[][] plot) {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				if (plot[i][j] != null)
					output.append("[*]");
				else
					output.append("[ ]");
			}
			output.append("\n");
		}

		System.out.println(output.toString());
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
