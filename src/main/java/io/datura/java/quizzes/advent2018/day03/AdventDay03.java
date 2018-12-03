package io.datura.java.quizzes.advent2018.day03;

public class AdventDay03 {
	// it's specifically stated to be a square, so
	// the l & w are equal
	// private static int FABRIC_LEN = 1000;
	private static int FABRIC_LEN = 10;

	// the claims all fit the same pattern, so we'll employ a regex
	// to match each part so we can 
	
	public static void main(String[] args) {
		char[][] sheet = new char[FABRIC_LEN][FABRIC_LEN];
		printFabricLayout(sheet);
	}

	private static void printFabricLayout(char[][] rows) {
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
