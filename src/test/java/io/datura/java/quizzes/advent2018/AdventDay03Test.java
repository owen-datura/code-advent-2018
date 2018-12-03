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
	
	@Test
	public void testOverlapExample() {
		int width = 8;
		int height = 8;
		char[][] sheet = new char[height][width];
		
		FabricClaim claim = FabricClaim.parseClaim("#1 @ 1,3: 4x4");
		FabricClaim claim2 = FabricClaim.parseClaim("#2 @ 3,1: 4x4");
		FabricClaim claim3 = FabricClaim.parseClaim("#3 @ 5,5: 2x2");
		
		AdventDay03.markClaim(sheet, claim);
		AdventDay03.markClaim(sheet, claim2);
		AdventDay03.markClaim(sheet, claim3);
		
		AdventDay03.printFabricLayout(sheet);
	}
}
