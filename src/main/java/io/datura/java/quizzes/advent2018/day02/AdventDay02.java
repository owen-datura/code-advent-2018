package io.datura.java.quizzes.advent2018.day02;

public class AdventDay02 {
	private static int ASCII_NUM_LOWERCASE = 26;
	private static int ASCII_START_VALUE = 97;

	private static String[] boxCodes = { "abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab" };

	public static void main(String[] args) {
		int[] result = countOccurrence("aabbcddeeefz");
		System.out.println(printCharCountArray(result));
	}

	private static int[] countOccurrence(String vals) {
		int[] counts = new int[ASCII_NUM_LOWERCASE];
		for (char c : vals.toCharArray()) {
			int p = (c - 0) - ASCII_START_VALUE;
			counts[p]++;
		}
		return counts;
	}

	public static String printCharCountArray(int[] counts) {
		StringBuilder output = new StringBuilder();
		
		for (int i = 0; i < ASCII_NUM_LOWERCASE; i++) {
			char c = (char) (ASCII_START_VALUE + i);
			output.append(c);
			output.append(":\t");
			output.append(counts[i]);
			output.append("\n");
		}
		
		return output.toString();
	}
}
