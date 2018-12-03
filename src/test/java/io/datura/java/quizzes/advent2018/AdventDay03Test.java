package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day03.AdventDay03;
import io.datura.java.quizzes.advent2018.day03.FabricClaim;

public class AdventDay03Test {

	@Test
	public void testClaimConversion() {
		String claimText = "#2 @ 3,1: 4x4";
		FabricClaim claim = FabricClaim.parseClaim(claimText);
		assertEquals(2, claim.getClaimId());
		assertEquals(3, claim.getLeftMargin());
		assertEquals(1, claim.getTopMargin());
		assertEquals(4, claim.getWidth());
		assertEquals(4, claim.getHeight());
	}

	@Test
	public void testInitialExample() {
		int width = 11;
		int height = 9;
		char[][] sheet = new char[height][width];

		FabricClaim claim = FabricClaim.parseClaim("#123 @ 3,2: 5x4");
		AdventDay03.markClaim(sheet, claim);
		//AdventDay03.printFabricLayout(sheet);
		int populated = AdventDay03.getFabricUtilization(sheet);
		assertEquals(20, populated);
	}
}
