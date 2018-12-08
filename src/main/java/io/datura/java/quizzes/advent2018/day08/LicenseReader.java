package io.datura.java.quizzes.advent2018.day08;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class LicenseReader {
	public static void main(String[] args) {
		String exampleLicense = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
		List<String> values = Arrays.asList(exampleLicense.split(" "));
		ListIterator<String> vi = values.listIterator();

		Node rootNode = createNode(vi);
	}

	private static Node createNode(ListIterator<String> valueIterator) {
		Node r = new Node();

		// first is the number of children
		Integer children = Integer.parseInt(valueIterator.next());
		r.getHeader().setNumChildren(children);

		// second is the number of metadatums(?)
		Integer meta = Integer.parseInt(valueIterator.next());
		r.getHeader().setNumMetadata(meta);

		for (int i = 1; i <= children; i++) {
			Node c = createNode(valueIterator);
			r.addChild(c);
		}

		return r;
	}
}
