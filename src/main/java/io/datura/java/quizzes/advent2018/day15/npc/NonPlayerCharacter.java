package io.datura.java.quizzes.advent2018.day15.npc;

import io.datura.java.quizzes.advent2018.day15.GameEntity;

public abstract class NonPlayerCharacter implements GameEntity {
	int orderOfAttack;
	int hitPoints = 300;
	int attackPower = 3;

	public int getOrderOfAttack() {
		return orderOfAttack;
	}

	public void setOrderOfAttack(int orderOfAttack) {
		this.orderOfAttack = orderOfAttack;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void doDamage(int minusHP) {
		this.hitPoints = Math.max((this.hitPoints - minusHP), 0);
	}

	public int getAttackPower() {
		return attackPower;
	}
}
