package io.datura.java.quizzes.advent2018.day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.datura.java.quizzes.advent2018.util.AdventUtils;

public class FabricClaim {
	private int claimId;
	private int leftMargin;
	private int topMargin;
	private int width;
	private int height;

	// the claims are all in a standard format, so we'll leverage that to parse it into a proper object
	public static final String INPUT_PATTERN = "[#](\\d+)\\s[@]\\s(\\d+)[,](\\d+)[:]\\s(\\d+)[x](\\d+)";
	public static final Pattern inputPattern = Pattern.compile(INPUT_PATTERN);

	public FabricClaim() {
	}

	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public int getLeftMargin() {
		return leftMargin;
	}

	public void setLeftMargin(int leftMargin) {
		this.leftMargin = leftMargin;
	}

	public int getTopMargin() {
		return topMargin;
	}

	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static FabricClaim parseClaim(String claimText) {
		Matcher matcher = inputPattern.matcher(claimText);

		if (!matcher.matches())
			return null;

		// group #1 is the claim id
		Integer claimId = getClaimValue(matcher, 1);
		// group #2 is the left margin
		Integer marginLeft = getClaimValue(matcher, 2);
		// group #3 is the top margin
		Integer marginTop = getClaimValue(matcher, 3);
		// group #4 is the width
		Integer width = getClaimValue(matcher, 4);
		// group #5 is the height
		Integer height = getClaimValue(matcher, 5);

		FabricClaim claim = new FabricClaim();
		claim.setClaimId(claimId);
		claim.setLeftMargin(marginLeft);
		claim.setTopMargin(marginTop);
		claim.setWidth(width);
		claim.setHeight(height);
		return claim;
	}

	private static int getClaimValue(Matcher matcher, int idx) {
		String value = matcher.group(idx);
		return !AdventUtils.isEmpty(value) ? Integer.parseInt(value) : Integer.valueOf(0);
	}
}
