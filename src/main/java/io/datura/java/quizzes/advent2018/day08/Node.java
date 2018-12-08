package io.datura.java.quizzes.advent2018.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Node {
	private final NodeHeader header;
	private final List<Node> children;
	private final NodeMetaData metadata;

	public Node() {
		this.header = new NodeHeader();
		this.children = new ArrayList<>();
		this.metadata = new NodeMetaData();
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	public NodeHeader getHeader() {
		return header;
	}

	public void addMetaData(Integer value) {
		metadata.addMetaData(value);
	}

	public void addChild(Node child) {
		children.add(child);
	}

	public ListIterator<Node> childIterator() {
		return children.listIterator();
	}

	public NodeMetaData getMetaData() {
		return metadata;
	}

	public List<Node> getMetaDataFilteredChildren() {
		if (getMetaData() == null || !getMetaData().hasMetaDataEntries())
			return Collections.emptyList();

		List<Node> filteredChildren = new ArrayList<>();
		for (Integer mv : getMetaData().metadata) {
			if (mv <= 0)
				continue;

			// the metadata value uses 1 to refer to 'the first'
			// metadata value, 2 to 'the second' etc. the array
			// that contains the value is zero indexed, so adjust
			// the value as appropriate
			int idx = --mv;
			try {
				Node c = children.get(idx);
				filteredChildren.add(c);
			} catch (IndexOutOfBoundsException iobe) {
				System.err.println("Index value of " + idx + " doesn't exist.");
				continue;
			}
		}

		return filteredChildren;
	}

	public class NodeHeader {
		private int numChildren;
		private int numMetadata;

		public int getNumChildren() {
			return numChildren;
		}

		public void setNumChildren(int numChildren) {
			this.numChildren = numChildren;
		}

		public int getNumMetadata() {
			return numMetadata;
		}

		public void setNumMetadata(int numMetadata) {
			this.numMetadata = numMetadata;
		}
	}

	public class NodeMetaData {
		private List<Integer> metadata;

		public NodeMetaData() {
			this.metadata = new ArrayList<>();
		}

		public void addMetaData(Integer value) {
			metadata.add(value);
		}

		public int sumMetaData() {
			return metadata.stream().reduce(0, Integer::sum);
		}

		public boolean hasMetaDataEntries() {
			return !metadata.isEmpty();
		}
	}
}
