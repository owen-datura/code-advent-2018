package io.datura.java.quizzes.advent2018.day15;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import io.datura.java.quizzes.advent2018.day15.npc.Elf;
import io.datura.java.quizzes.advent2018.day15.npc.Goblin;

public class Game {
	public static void main(String[] args) {
		GameWorld world = new GameWorld(7, 5);

		// row 1
		Pair<Integer, Integer> dest = new ImmutablePair<>(2, 1);
		GameEntity obj = new Goblin();
		world.setEntityAt(dest, obj);

		dest = new ImmutablePair<Integer, Integer>(4, 1);
		obj = new Elf();
		world.setEntityAt(dest, obj);

		// row 2
		dest = new ImmutablePair<Integer, Integer>(1, 2);
		obj = new Elf();
		world.setEntityAt(dest, obj);
		
		dest = new ImmutablePair<Integer, Integer>(3, 2);
		obj = new Goblin();
		world.setEntityAt(dest, obj);
		
		dest = new ImmutablePair<Integer, Integer>(5, 2);
		obj = new Elf();
		world.setEntityAt(dest, obj);

		// row 3
		dest = new ImmutablePair<Integer, Integer>(2, 3);
		obj = new Goblin();
		world.setEntityAt(dest, obj);
		
		dest = new ImmutablePair<Integer, Integer>(4, 3);
		obj = new Elf();
		world.setEntityAt(dest, obj);
		
		world.startRound();
		
		System.out.println(world.getTextualGameWorld());
	}
}
