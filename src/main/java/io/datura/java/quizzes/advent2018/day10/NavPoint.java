package io.datura.java.quizzes.advent2018.day10;

public class NavPoint {
	private final int origX;
	private final int origY;

	private final int deltaX;
	private final int deltaY;

	private int posX;
	private int posY;

	public NavPoint(int x, int y, int deltaX, int deltaY) {
		this.origX = x;
		this.origY = y;

		// when initialized, we'll make the 'current' x & y
		// match the values we were supplied
		this.posX = x;
		this.posY = y;

		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public void tick() {
		// advance the current state of the coordinates by one iteration
		posX += deltaX;
		posY += deltaY;
	}

	private static int processCorrection(int range, int val) {
		int origin = range / 2;
		return origin + val;
	}

	public int getPosX() {
		return posX;
	}

	public int getCorrectedPosX(int xRange) {
		return processCorrection(xRange, posX);
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getCorrectedPosY(int yRange) {
		return processCorrection(yRange, posY);
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getOrigX() {
		return origX;
	}

	public int getOrigY() {
		return origY;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}
}
