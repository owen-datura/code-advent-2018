package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day03.AdventDay03a;
import io.datura.java.quizzes.advent2018.day03.FabricClaim;

public class AdventDay03aTest {
	@Test
	public void testInitialExample() {
		int width = 11;
		int height = 9;
		FabricClaim[][][] sheet = new FabricClaim[height][width][];

		FabricClaim claim = FabricClaim.parseClaim("#123 @ 3,2: 5x4");
		AdventDay03a.markClaim(sheet, claim);
		//AdventDay03a.printFabricLayout(sheet);
		int populated = AdventDay03a.getFabricUtilization(sheet);
		assertEquals(20, populated);
	}
	
	@Test
	public void testPerfectOverlap() {
		int width = 11;
		int height = 9;
		FabricClaim[][][] sheet = new FabricClaim[height][width][];

		FabricClaim claim = FabricClaim.parseClaim("#123 @ 3,2: 5x4");
		FabricClaim claim2 = FabricClaim.parseClaim("#456 @ 3,2: 5x4");
		AdventDay03a.markClaim(sheet, claim);
		AdventDay03a.markClaim(sheet, claim2);
		//AdventDay03a.printFabricLayout(sheet);
		// there's two exactly overlapping claims here, so again there'll
		// be 20 overlapping square inches
		int conflicting = AdventDay03a.getFabricConflicts(sheet);
		assertEquals(20, conflicting);
	}
	
	@Test
	public void testOverlapExample() {
		int width = 8;
		int height = 8;
		FabricClaim[][][] sheet = new FabricClaim[height][width][];
		
		FabricClaim claim = FabricClaim.parseClaim("#1 @ 1,3: 4x4");
		FabricClaim claim2 = FabricClaim.parseClaim("#2 @ 3,1: 4x4");
		FabricClaim claim3 = FabricClaim.parseClaim("#3 @ 5,5: 2x2");

		AdventDay03a.markClaim(sheet, claim);
		AdventDay03a.markClaim(sheet, claim2);
		AdventDay03a.markClaim(sheet, claim3);

		//AdventDay03a.printFabricLayout(sheet);
		
		int conflicts = AdventDay03a.getFabricConflicts(sheet);
		assertEquals(4, conflicts);
	}
	
	@Test
	public void testNoConflictState() {
		int width = 8;
		int height = 8;
		FabricClaim[][][] sheet = new FabricClaim[height][width][];
		
		FabricClaim claim = FabricClaim.parseClaim("#1 @ 1,3: 4x4");
		FabricClaim claim2 = FabricClaim.parseClaim("#2 @ 3,1: 4x4");
		// in this case claim 3 exists independently of the others (which overlap)
		FabricClaim claim3 = FabricClaim.parseClaim("#3 @ 5,5: 2x2");

		AdventDay03a.markClaim(sheet, claim);
		AdventDay03a.markClaim(sheet, claim2);
		AdventDay03a.markClaim(sheet, claim3);

		//AdventDay03a.printFabricLayout(sheet);
		
		Collection<Integer> noConflictClaims = AdventDay03a.getNonConflictingClaims(sheet);
		assertEquals(1, noConflictClaims.size());

		int claimId = noConflictClaims.iterator().next();
		assertEquals(3, claimId);
	}
}
