package io.datura.java.quizzes.advent2018.day08;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private NodeHeader header;
	private List<Node> children;
	private NodeMetaData metadata;

	public Node() {
		this.header = new NodeHeader();
		this.children = new ArrayList<>();
		this.metadata = new NodeMetaData();
	}

	public NodeHeader getHeader() {
		return header;
	}

	public NodeMetaData getMetaData() {
		return metadata;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}

	class NodeHeader {
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

	class NodeMetaData {
		private List<Integer> metadata;

		public NodeMetaData() {
			this.metadata = new ArrayList<>();
		}

		public void addMetaData(Integer value) {
			metadata.add(value);
		}
	}
}
