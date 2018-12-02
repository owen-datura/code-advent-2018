package io.datura.java.quizzes.advent2018.day02;

import java.util.BitSet;
import java.util.Collections;
import java.util.List;

public class BoxCode implements Comparable<BoxCode> {
	private final String code;
	private int closestDistance;
	private BoxCode closestCode;

	public BoxCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public int getClosestDistance() {
		return closestDistance;
	}

	public void setClosestDistance(int closestDistance) {
		this.closestDistance = closestDistance;
	}

	public String getClosestMatchingCode() {
		return this.closestCode.getCode();
	}

	public void setClosestCode(BoxCode closestCode) {
		this.closestCode = closestCode;
	}

	public void evaluate(BoxCode bc) {
		// evaluate this code against the foreign one
		int highBits = getNumHighBits(calculateHammingDistanceBitSet(this, bc));

		// assist in doing an early bail-out by checking if there's *any* matching
		// characters before determining if the existing comparison (if any)
		// is weaker than the one we just computed
		if (highBits > 0 && closestDistance < highBits) {
			this.setClosestDistance(highBits);
			this.setClosestCode(bc);
			
			// our comparison is transitive, so we'll set a value
			// on the foreign box code if applicable
			if (bc.getClosestDistance() == 0 || bc.getClosestDistance() < highBits) {
				bc.setClosestDistance(highBits);
				bc.setClosestCode(this);
			}
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

	public static BoxCode getClosestMatch(List<BoxCode> codes) {
		if (codes == null || codes.isEmpty())
			return null;

		// perform a sort against the collection, placing the
		// record w/ the closest match at the top
		Collections.sort(codes, Collections.reverseOrder());

		// then pick the closest match
		return codes.get(0);
	}

	@Override
	public int compareTo(BoxCode o) {
		Integer d = Integer.valueOf(this.getClosestDistance());
		Integer d1 = Integer.valueOf(o.getClosestDistance());
		int compare = d.compareTo(d1);
		return compare;
	}
}
