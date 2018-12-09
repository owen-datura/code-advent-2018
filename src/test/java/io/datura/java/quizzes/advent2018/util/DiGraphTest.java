package io.datura.java.quizzes.advent2018.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.util.DiGraph.Vertex;

public class DiGraphTest {
	@Test
	public void testAddVertexPair() {
		DiGraph graph = new DiGraph();
		// 'C' requires 'A'
		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('A'));
		// two vertices have been added
		assertEquals(2, graph.getVertices());
		// 'C' should already exist, but 'F' is created
		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('F'));
		assertEquals(3, graph.getVertices());
		// now we'll create a 'D' which also refers to 'A'.
		graph.addVertexPair(Character.valueOf('D'), Character.valueOf('A'));
		// 'A's indegree should reflect the references being made by C and D
		assertEquals(2, graph.getIndegreeByIdentifier(Character.valueOf('A')));
	}

	@Test
	public void testZeroReferenceVertex() {
		DiGraph graph = new DiGraph();
		
		graph.addVertexPair(Character.valueOf('D'), Character.valueOf('A'));
		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('A'));
		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('F'));
		graph.addVertexPair(Character.valueOf('B'), Character.valueOf('C'));
		
		// in the above scenario there should be two vertices with zero indegree,
		// B & D. D is created first, but the requirements stipulate that
		// the 'first' (sort by alpha) value should be considered, so test this
		Vertex zeroRef = graph.getZeroReferenceVertex();
		assertEquals(Character.valueOf('B'), zeroRef.getIdentifier());
	}
}
