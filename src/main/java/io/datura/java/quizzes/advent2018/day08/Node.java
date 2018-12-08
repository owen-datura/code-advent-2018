package io.datura.java.quizzes.advent2018.day08;

import java.util.ArrayList;
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
	}
}
