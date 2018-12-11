package io.datura.java.quizzes.advent2018.day10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NavPoint {
	private final int origX;
	private final int origY;

	private final int deltaX;
	private final int deltaY;

	private int posX;
	private int posY;

	public static final String INPUT_PATTERN = "position=<([-?\\s]\\d+),\\s+([-?\\s]\\d+)>\\s+velocity=<([-?\\s]\\d+),\\s([-?\\s]\\d+)>";
	private static final Pattern inputRegex = Pattern.compile(INPUT_PATTERN);

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

	public int getPosX() {
		return posX;
	}

	public int getCorrectedPosX(int xRange) {
		int origin = xRange / 2;
		if (posX == 0)
			return origin;
		else
			return origin + posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getCorrectedPosY(int yRange) {
		int origin = yRange / 2;

		if (posY == 0)
			return origin;
		else if (posY > 0)
			return origin - posY;
		else
			return origin + Math.abs(posY);
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

	@Override
	public String toString() {
		return "NavPoint [origX=" + origX + ", origY=" + origY + ", deltaX=" + deltaX + ", deltaY=" + deltaY + ", posX="
				+ posX + ", posY=" + posY + "]";
	}

	public static NavPoint parseNavPoint(String input) {
		Matcher m = inputRegex.matcher(input);
		if (!m.matches())
			return null;

		// X
		String xVal = m.group(1);
		Integer x = Integer.parseInt(xVal.trim());

		// Y
		String yVal = m.group(2);
		Integer y = Integer.parseInt(yVal.trim());

		// dX
		String dXVal = m.group(3);
		Integer dx = Integer.parseInt(dXVal.trim());

		// dY
		String dYVal = m.group(4);
		Integer dy = Integer.parseInt(dYVal.trim());

		return new NavPoint(x, y, dx, dy);
	}
}
