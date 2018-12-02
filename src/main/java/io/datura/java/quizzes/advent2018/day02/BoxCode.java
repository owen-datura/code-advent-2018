package io.datura.java.quizzes.advent2018.day02;

import java.util.BitSet;

public class BoxCode {
	private final String code;
	private int closestDistance;
	private BoxCode closestCode;

	public BoxCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static void mapBoxCodes(String[] codes) {
		for (int i = 0, sz = codes.length; i < sz; i++) {
			
		}
	}

	public static int getNumHighBits(BitSet bs) {
		int bitsHigh = 0;
		for (int i = 0, sz = bs.length(); i < sz; i++) {
			if (bs.get(i))
				bitsHigh++;
		}

		return bitsHigh;
	}

	public static BitSet calculateHammingDistanceBitSet(BoxCode b1, BoxCode b2) {
		char[] bc1 = b1.getCode().toCharArray();
		char[] bc2 = b2.getCode().toCharArray();

		// there's no contract that ensures the arrays
		// are of equal length, so we'll need to handle
		// this ourselves
		int sz = Math.max(bc1.length, bc2.length);
		if (bc1.length < sz)
			bc1 = resizeArray(bc1, sz);
		else if (bc2.length < sz)
			bc2 = resizeArray(bc2, sz);

		// we'll evaluate each cell in the array and keep
		// track of the values as individual bits
		BitSet bs = new BitSet(sz);
		for (int i = 0; i < sz; i++) {
			char c1 = bc1[i];
			char c2 = bc2[i];

			if (c1 == c2)
				bs.set(i);
		}

		return bs;
	}

	public static char[] resizeArray(char[] ca, int sz) {
		char[] output = new char[sz];
		System.arraycopy(ca, 0, output, 0, ca.length);
		return output;
	}
}
