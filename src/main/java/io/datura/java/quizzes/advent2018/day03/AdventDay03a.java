package io.datura.java.quizzes.advent2018.day03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AdventDay03a {
	private static int FABRIC_LEN = 1000;

	public static final char USAGE_NORMAL = 'o';
	public static final char USAGE_CONFLICT = 'X';

	public static void main(String[] args) {
		try {
			// load the claims from the provided data file
			Collection<FabricClaim> claims = parseFabricClaims(getDataFromInputFile());
			// it's specifically stated to be a square, so
			// the l & w are equal
			// 3a modification: we'll leave the last cell unassigned
			// so it can accommodate ragged results
			FabricClaim[][][] sheet = new FabricClaim[FABRIC_LEN][FABRIC_LEN][];
			for (FabricClaim claim : claims) {
				markClaim(sheet, claim);
			}

			Collection<Integer> noConflictClaims = getNonConflictingClaims(sheet);
			System.out.println(noConflictClaims);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O exception when processing input. Please check configuration.");
			System.exit(1);
		}
	}

	public static void markClaim(FabricClaim[][][] sheet, FabricClaim claim) {
		// the margin defines our starting point
		// we'll want to track the *initial* column
		final int initialCol = claim.getLeftMargin();
		// then use the initial value to determine the maximum
		final int maxCol = initialCol + claim.getWidth();
		// similarly, we need to determine the max row so we can iterate
		final int maxRow = claim.getTopMargin() + claim.getHeight();

		for (int rowOffset = claim.getTopMargin(); rowOffset < maxRow; rowOffset++) {
			for (int colOffset = initialCol; colOffset < maxCol; colOffset++) {
				FabricClaim[] claims = sheet[rowOffset][colOffset];
				if (claims == null || claims.length == 0) {
					// the third cell contains no records here, so we must
					// not have visited it before. create a new single-
					// entry array for the current claim, then set
					// it on the master sheet
					claims = new FabricClaim[] { claim };
					sheet[rowOffset][colOffset] = claims;
				} else {
					// the third cell *does* contain an existing claim record,
					// so we must have been here before. this case brings
					// these claims into conflict, so set that state,
					// then update the existing cell so that it contains the
					// existing and newly-evaluated claim within it

					FabricClaim[] updatedClaims = Arrays.copyOf(claims, claims.length + 1);
					updatedClaims[updatedClaims.length - 1] = claim;
					for (FabricClaim c : Arrays.asList(updatedClaims))
						c.setHasConflict();

					sheet[rowOffset][colOffset] = updatedClaims;
				}
			}
		}
	}

	public static int getFabricUtilization(FabricClaim[][][] sheet) {
		int populatedCells = 0;
		for (int i = 0, rows = sheet.length; i < rows; i++) {
			for (int j = 0, cols = sheet[i].length; j < cols; j++) {
				FabricClaim[] vals = sheet[i][j];
				if (vals != null && vals.length > 0)
					populatedCells++;
			}
		}

		return populatedCells;
	}

	public static int getFabricConflicts(FabricClaim[][][] sheet) {
		int conflicts = 0;
		for (int i = 0, rows = sheet.length; i < rows; i++) {
			for (int j = 0, cols = sheet[i].length; j < cols; j++) {
				FabricClaim[] vals = sheet[i][j];
				if (vals != null && vals.length > 1)
					conflicts++;
			}
		}

		return conflicts;
	}

	public static void printFabricLayout(FabricClaim[][][] sheet) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0, rs = sheet.length; i < rs; i++) {
			FabricClaim[][] cols = sheet[i];
			for (int j = 0, cs = cols.length; j < cs; j++) {
				sb.append("[");

				FabricClaim[] fabricClaims = cols[j];
				// this cell is populated with a single claim
				if (fabricClaims == null || fabricClaims.length == 0)
					sb.append(".");
				else if (fabricClaims.length == 1)
					sb.append("o");
				// this cell is populated with more than one claim
				else if (fabricClaims.length > 1)
					sb.append(fabricClaims.length);

				sb.append("]");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	private static Collection<FabricClaim> parseFabricClaims(Collection<String> claims) {
		Collection<FabricClaim> convertedClaims = new ArrayList<>();
		for (String claim : claims) {
			convertedClaims.add(FabricClaim.parseClaim(claim));
		}

		return convertedClaims;
	}

	private static Collection<String> getDataFromInputFile() throws IOException {
		try {
			// the input file's stored relative to the class,
			// so retrieve it w/ the classloader
			ClassLoader classLoader = AdventDay03a.class.getClassLoader();
			Path p = Paths.get(classLoader.getResource("fabric-usage.txt").toURI());
			// Files.lines stealthily opens (and keeps open) a handle, so
			// we need to ensure that the stream it produces gets closed
			return Files.readAllLines(p);
		} catch (URISyntaxException urie) {
			throw new IOException("Unable to load input files, check file layout.");
		}
	}

	public static Collection<Integer> getNonConflictingClaims(FabricClaim[][][] sheet) {
		Set<Integer> nonConflictingClaims = new HashSet<>();
		for (int i = 0, rows = sheet.length; i < rows; i++) {
			for (int j = 0, cols = sheet[i].length; j < cols; j++) {
				FabricClaim[] claims = sheet[i][j];
				if (claims == null)
					continue;

				for (FabricClaim claim : Arrays.asList(claims)) {
					if (!claim.hasConflict())
						nonConflictingClaims.add(claim.getClaimId());
				}
			}
		}
		return nonConflictingClaims;
	}
}
