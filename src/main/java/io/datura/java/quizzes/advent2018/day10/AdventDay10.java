package io.datura.java.quizzes.advent2018.day10;

public class AdventDay10 {
	public static void main(String[] args) {
		NavPlot plot = new NavPlot();

		NavPoint p = new NavPoint(3, 9, 1, -2);
		plot.addPlotPoint(p);

		NavPoint[][] matrix = plot.createPlot();
		NavPlot.printPlot(matrix);

		plot.tick();
		matrix = plot.createPlot();
		NavPlot.printPlot(matrix);
	}
}
