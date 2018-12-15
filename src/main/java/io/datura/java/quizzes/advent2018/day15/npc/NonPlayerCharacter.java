package io.datura.java.quizzes.advent2018.day15.npc;

import io.datura.java.quizzes.advent2018.day15.GameEntity;

public abstract class NonPlayerCharacter implements GameEntity {
	int orderOfAttack;

	public int getOrderOfAttack() {
		return orderOfAttack;
	}
	
	public void setOrderOfAttack(int orderOfAttack) {
		this.orderOfAttack = orderOfAttack;
	}
}
