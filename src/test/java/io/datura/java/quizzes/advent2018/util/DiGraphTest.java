package io.datura.java.quizzes.advent2018.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

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
		Character zeroRefIdentifier = graph.getZeroReferenceVertex();
		assertEquals(Character.valueOf('B'), zeroRefIdentifier);
	}

	@Test
	public void testPopVertex() {
		DiGraph graph = new DiGraph();

		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('A'));
		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('F'));
		graph.addVertexPair(Character.valueOf('D'), Character.valueOf('A'));

		// A's indegree is 2, F's indegree is 1
		assertEquals(2, graph.getIndegreeByIdentifier(Character.valueOf('A')));
		assertEquals(1, graph.getIndegreeByIdentifier(Character.valueOf('F')));

		// there's two zero-indegree vertices, 'C' and 'D', so let's verify 
		// that's the case
		Character zeroRefIdentifier = graph.getZeroReferenceVertex();
		assertEquals(Character.valueOf('C'), zeroRefIdentifier);
		
		// now we'll pop C
		boolean result = graph.popVertexByIdentifier(Character.valueOf('C'));
		assertTrue(result);
		
		// the indegree of both A and F should now have been changed
		assertEquals(1, graph.getIndegreeByIdentifier(Character.valueOf('A')));
		assertEquals(0, graph.getIndegreeByIdentifier(Character.valueOf('F')));
	}
	
	@Test
	public void testSimpleGraph() {
		DiGraph graph = new DiGraph();
		graph.addVertexPair(Character.valueOf('D'), Character.valueOf('C'));
		graph.addVertexPair(Character.valueOf('C'), Character.valueOf('A'));
		List<Character> v = graph.resolve();
		assertEquals(Character.valueOf('D'), v.get(0));
		assertEquals(Character.valueOf('C'), v.get(1));
		assertEquals(Character.valueOf('A'), v.get(2));
	}
}
