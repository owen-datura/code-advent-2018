package io.datura.java.quizzes.advent2018.day10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonTest {
	public static void main(String[] args) {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(NavPlot.class, new NavPlotTypeAdapter());
		gb.setPrettyPrinting();
		Gson gson = gb.create();

		NavPlot plot = new NavPlot();
		NavPoint p = new NavPoint(9, 1, 0, 2);
		plot.addPlotPoint(p);
		p = new NavPoint(7, 0, -1, 0);
		plot.addPlotPoint(p);
		p = new NavPoint(6, 10, 2, -2);
		plot.addPlotPoint(p);

		String jsonOutput = gson.toJson(plot);
		System.out.println(jsonOutput);
	}
}
