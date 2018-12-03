package io.datura.java.quizzes.advent2018.day03;

public class AdventDay03 {
	// private static int FABRIC_LEN = 1000;
	private static int FABRIC_LEN = 10;

	public static void main(String[] args) {
		// it's specifically stated to be a square, so
		// the l & w are equal
		char[][] sheet = new char[FABRIC_LEN][FABRIC_LEN];
		printFabricLayout(sheet);
	}

	public static void markClaim(char[][] sheet, FabricClaim claim) {
		// the margin defines our starting point
		// we'll want to track the *initial* column
		int initialCol = claim.getLeftMargin();
		// then use the initial value to determine the maximum
		int maxCol = initialCol + claim.getWidth();
		// similarly, we need to determine the max row so we can iterate
		int maxRow = claim.getTopMargin() + claim.getHeight();
		
		for (int rowOffset = claim.getTopMargin(); rowOffset < maxRow; rowOffset++) {
			for (int colOffset = initialCol; colOffset < maxCol; colOffset++) {
				sheet[rowOffset][colOffset] = 'o';
			}
		}
	}

	public static int getFabricUtilization(char[][] sheet) {
		int populatedCells = 0;
		for (int i = 0, rows = sheet.length; i < rows; i++) {
			for (int j = 0, cols = sheet[i].length; j < cols; j++) {
				char val = sheet[i][j];
				if( val != 0 )
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
}
