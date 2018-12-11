package io.datura.java.quizzes.advent2018.day10;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class NavPlot {
	private Collection<NavPoint> points = new ArrayList<>();
	private Map<Integer, Set<Integer>> plotByRow = new HashMap<>();
	private static final Pair<Integer, Integer> ORIGIN = new ImmutablePair<Integer, Integer>(0, 0);

	public void tick() {
		// iterate all plot points, using their velocity values to
		// alter their location in space
		points.stream().forEach(NavPoint::tick);
		// updating the locations has invalidated the plot, so clear it
		plotByRow.clear();
	}

	public void addPlotPoint(NavPoint p) {
		if (p == null)
			return;

		points.add(p);
	}

	public void configurePlot() {
		// establish the plot dimensions
		Pair<Integer, Integer> dim = getPlotDim(points);

		int dimX = dim.getLeft();
		int dimY = dim.getRight();

		for (NavPoint point : points) {
			int rowIdx = point.getCorrectedPosY(dimY);
			if (plotByRow.containsKey(rowIdx)) {
				plotByRow.get(rowIdx).add(point.getCorrectedPosX(dimX));
			} else {
				Set<Integer> rowValues = new TreeSet<>();
				rowValues.add(point.getCorrectedPosX(dimX));
				plotByRow.put(rowIdx, rowValues);
			}
		}
	}

	public void printPlot(Writer out) throws IOException {
		Pair<Integer, Integer> dim = getPlotDim(points);

		int dimY = dim.getRight();
		int dimX = dim.getLeft();

		for (int y = 0; y < dimY; y++) {
			if (plotByRow.containsKey(y)) {
				generateRowOutput(out, plotByRow.get(y), dimX);
			} else {
				generateEmptyRow(out, dimX);
			}
		}
	}

	private static void generateEmptyRow(Writer out, int numCols) throws IOException {
		for (int c = 0; c < numCols; c++) {
			out.write(".");
		}
		out.write("\n");
	}

	private static void generateRowOutput(Writer out, Set<Integer> setCols, int numCols) throws IOException {
		for (int c = 0; c < numCols; c++) {
			if (setCols.contains(c))
				out.write("#");
			else
				out.write(".");
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
