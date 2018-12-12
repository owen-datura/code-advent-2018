package io.datura.java.quizzes.advent2018.day10;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class NavPlotTypeAdapter extends TypeAdapter<NavPlot> {
	public enum PlotMode {
		MARKERS
	};

	public enum PlotType {
		SCATTER
	};

	public static final String MODE_KEY = "mode";
	public static final String TYPE_KEY = "type";

	@Override
	public void write(JsonWriter writer, NavPlot plot) throws IOException {
		writer.beginObject();

		Pair<List<Integer>, List<Integer>> points = plot.getPlotPoints();
		int numX = points.getLeft().size();
		int numY = points.getRight().size();
		if (numX == 0 || numY == 0 || numX != numY)
			throw new IOException("Point pair is in an inconsistent state, can't continue.");

		// x array
		writer.name("x");
		writer.beginArray();
		for (Integer xs : points.getLeft()) {
			writer.value(xs);
		}
		writer.endArray();

		// y array
		writer.name("y");
		writer.beginArray();
		for (Integer ys : points.getRight()) {
			writer.value(ys);
		}
		writer.endArray();

		writer.name(MODE_KEY);
		writer.value(PlotMode.MARKERS.name().toLowerCase());

		writer.name(TYPE_KEY);
		writer.value(PlotType.SCATTER.name().toLowerCase());

		writer.endObject();
	}

	@Override
	public NavPlot read(JsonReader in) throws IOException {
		throw new IOException("Unmarshal operation is not supported.");
	}
}
