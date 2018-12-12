package io.datura.java.quizzes.advent2018.day10;

import java.io.IOException;

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
	public void write(JsonWriter writer, NavPlot value) throws IOException {
		writer.beginObject();

		// x array
		writer.name("x");
		writer.beginArray();
		writer.value(1l);
		writer.value(2l);
		writer.endArray();

		// y array
		writer.name("y");
		writer.beginArray();
		writer.value(0l);
		writer.value(4l);
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
