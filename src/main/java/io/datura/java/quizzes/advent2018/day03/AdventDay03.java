package io.datura.java.quizzes.advent2018.day03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import io.datura.java.quizzes.advent2018.day01.AdventDay01;

public class AdventDay03 {
	private static int FABRIC_LEN = 1000;

	public static final char USAGE_NORMAL = 'o';
	public static final char USAGE_CONFLICT = 'X';

	public static void main(String[] args) {
		try {
			// load the claims from the provided data file
			Collection<FabricClaim> claims = parseFabricClaims(getDataFromInputFile());
			// it's specifically stated to be a square, so
			// the l & w are equal
			char[][] sheet = new char[FABRIC_LEN][FABRIC_LEN];
			for (FabricClaim claim : claims) {
				markClaim(sheet, claim);
			}

			printFabricLayout(sheet);

			int conflicts = getFabricConflicts(sheet);
			String output = String.format("Calculation of fabric claims complete. There were %d conflicts.", conflicts);
			System.out.println(output);
		} catch (IOException ioe) {
			System.err.println("Encountered an I/O exception when processing input. Please check configuration.");
			System.exit(1);
		}
	}

	public static void markClaim(char[][] sheet, FabricClaim claim) {
		// the margin defines our starting point
		// we'll want to track the *initial* column
		final int initialCol = claim.getLeftMargin();
		// then use the initial value to determine the maximum
		final int maxCol = initialCol + claim.getWidth();
		// similarly, we need to determine the max row so we can iterate
		final int maxRow = claim.getTopMargin() + claim.getHeight();

		for (int rowOffset = claim.getTopMargin(); rowOffset < maxRow; rowOffset++) {
			for (int colOffset = initialCol; colOffset < maxCol; colOffset++) {
				char curVal = sheet[rowOffset][colOffset];
				if (curVal == 0)
					sheet[rowOffset][colOffset] = USAGE_NORMAL;
				else
					sheet[rowOffset][colOffset] = USAGE_CONFLICT;
			}
		}
	}

	public static int getFabricUtilization(char[][] sheet) {
		int populatedCells = 0;
		for (int i = 0, rows = sheet.length; i < rows; i++) {
			for (int j = 0, cols = sheet[i].length; j < cols; j++) {
				char val = sheet[i][j];
				if (val != 0)
					populatedCells++;
			}
		}

		return populatedCells;
	}

	public static int getFabricConflicts(char[][] sheet) {
		int populatedCells = 0;
		for (int i = 0, rows = sheet.length; i < rows; i++) {
			for (int j = 0, cols = sheet[i].length; j < cols; j++) {
				char val = sheet[i][j];
				if (val == USAGE_CONFLICT)
					populatedCells++;
			}
		}

		return populatedCells;
	}

	public static void printFabricLayout(char[][] rows) {
		StringBuilder so = new StringBuilder();

		for (int i = 0, rs = rows.length; i < rs; i++) {
			char[] cols = rows[i];
			for (int j = 0, cs = cols.length; j < cs; j++) {
				so.append("[");

				char c = cols[j];
				// the default initialization sets c to ASCII 0
				// (which is unprintable), so we need to special
				// case that (or otherwise we'd need
				// to prefill the array)
				so.append(c != 0 ? c : '.');

				so.append("]");
			}
			// row is complete, move to the next line
			so.append("\n");
		}

		System.out.println(so.toString());
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
			ClassLoader classLoader = AdventDay01.class.getClassLoader();
			Path p = Paths.get(classLoader.getResource("fabric-usage.txt").toURI());
			// Files.lines stealthily opens (and keeps open) a handle, so
			// we need to ensure that the stream it produces gets closed
			return Files.readAllLines(p);
		} catch (URISyntaxException urie) {
			throw new IOException("Unable to load input files, check file layout.");
		}
	}
}
