package io.datura.java.quizzes.advent2018.day15;

import java.util.Arrays;

import io.datura.java.quizzes.advent2018.day15.terrain.Floor;
import io.datura.java.quizzes.advent2018.day15.terrain.Wall;

public class GameWorld {
	private final GameEntity[][] world;

	private final int x;
	private final int y;

	public GameWorld(int x, int y) {
		this.x = x;
		this.y = y;

		world = new GameEntity[y][x];
		initializeWorld();
	}

	private void initializeWorld() {
		for (int rowIdx = 0; rowIdx < world.length; rowIdx++) {
			// the first and last row are an encircling wall
			if (rowIdx == 0 || rowIdx == world.length - 1) {
				fillRow(world[rowIdx], Wall.WALL);
				continue;
			}

			int rowLen = world[rowIdx].length;
			for (int colIdx = 0; colIdx < rowLen; colIdx++) {
				// the first and last columns of a non-all wall row
				// are themselves walls
				if (colIdx == 0 || colIdx == rowLen - 1) {
					world[rowIdx][colIdx] = Wall.WALL;
					continue;
				}

				// otherwise, mark it as walkable space
				// TODO implement inner walls
				world[rowIdx][colIdx] = Floor.FLOOR;
			}
		}
	}

	private void fillRow(GameEntity[] row, GameEntity val) {
		Arrays.fill(row, val);
	}

	public String getTextualGameWorld() {
		int sz = x * y;
		StringBuilder output = new StringBuilder(sz);
		for (int row = 0; row < world.length; row++) {
			for (int col = 0; col < world[row].length; col++) {
				output.append(world[row][col].getOutputSymbol());
			}
			output.append("\n");
		}
		return output.toString();
	}
}
