package io.datura.java.quizzes.advent2018.day10;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class NavPlot {
	private Collection<NavPoint> points = new ArrayList<>();

	private static final Pair<Integer, Integer> ORIGIN = new ImmutablePair<Integer, Integer>(0, 0);

	public void tick() {
		points.stream().forEach(NavPoint::tick);
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

	public static void printPlot(NavPoint[][] plot, Writer out) throws IOException {
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				if (plot[i][j] != null)
					out.write("#");
				else
					out.write(".");
			}
			out.write("\n");
		}
		
		out.write("\n");
	}

	public static Pair<Integer, Integer> getPlotDim(Collection<NavPoint> points) {
		if (points == null || points.size() == 0)
			return ORIGIN;

		/*
		 * Instead of creating a 'ragged' array about the origin, we'll find the
		 * 'largest' X/Y value (accounting for its appearance on the negative side of
		 * the graph) and use that to set both + & -, adding one to account for the
		 * origin space.
		 */
		int Xdim = 0;
		int Ydim = 0;
		for (NavPoint p : points) {
			Xdim = Math.max(Math.abs(p.getPosX()), Xdim);
			Ydim = Math.max(Math.abs(p.getPosY()), Ydim);
		}

		int Xrange = (Xdim * 2) + 1;
		int Yrange = (Ydim * 2) + 1;

		// expanding the plot doesn't matter here, so make the range
		// easily divisible for when it comes time to adjust the point locations
		if (Xrange % 2 != 0)
			Xrange++;

		if (Yrange % 2 != 0)
			Yrange++;

		return new ImmutablePair<Integer, Integer>(Xrange, Yrange);
	}
}
