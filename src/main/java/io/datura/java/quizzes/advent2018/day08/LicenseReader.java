package io.datura.java.quizzes.advent2018.day08;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class LicenseReader {
	public static Node createNodeFromLicenseString(String license) {
		List<String> values = Arrays.asList(license.split(" "));
		ListIterator<String> valueIterator = values.listIterator();
		return createNode(valueIterator);
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

		for (int i = 1; i <= meta; i++) {
			Integer metaDataValue = Integer.parseInt(valueIterator.next());
			r.addMetaData(metaDataValue);
		}

		return r;
	}

	public static int altSumMetaData(Node node) {
		int sum = 0;

		if (!node.hasChildren()) {
			// no children are defined, so use the normal metadata handling
			sum += node.getMetaData().sumMetaData();
		} else {
			// this node has children, so create a filtered subset that includes
			// only those that have a corresponding metadata entry
			ListIterator<Node> childIterator = node.getMetaDataFilteredChildren().listIterator();
			// then sum them recursively
			while (childIterator.hasNext()) {
				sum += altSumMetaData(childIterator.next());
			}
		}

		return sum;
	}

	public static int sumMetaData(Node node) {
		int sum = 0;

		// recursively find the sum of the children
		ListIterator<Node> childIterator = node.childIterator();
		while (childIterator.hasNext()) {
			sum += sumMetaData(childIterator.next());
		}

		// with the children accounted for, add the current node's metadata values
		sum += node.getMetaData().sumMetaData();

		return sum;
	}
}
