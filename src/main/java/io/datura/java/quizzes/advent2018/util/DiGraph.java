package io.datura.java.quizzes.advent2018.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DiGraph {
	private Map<Character, Vertex> v = new HashMap<>();
	private Map<Vertex, Set<Vertex>> adj = new HashMap<>();

	public void addVertexPair(Character precedes, Character target) {
		Vertex p = v.get(precedes);
		if (p == null) {
			// we've never seen this particular vertex before,
			// so handle setting it up
			p = new Vertex(precedes);
			v.put(precedes, p);
			adj.put(p, new LinkedHashSet<>());
		}

		Vertex t = v.get(target);
		if (t == null) {
			t = new Vertex(target);
			v.put(target, t);
			adj.put(t, new LinkedHashSet<>());
		}

		// add the target to the preceding vertex's adjacency list
		if (adj.get(p).add(t)) {
			// and increment the indegree of the target
			t.addReference();
		}
	}

	public List<Character> resolve() {
		if (v.isEmpty())
			return Collections.emptyList();

		Character cv = getZeroReferenceVertex();
		if (cv == null)
			throw new IllegalStateException(
					"Resolution failure! There does not appear to be a suitable Vertex from which to start.");

		List<Character> rv = new ArrayList<>();
		while (cv != null) {
			popVertexByIdentifier(cv);
			rv.add(cv);
			cv = getZeroReferenceVertex();
		}

		return rv;
	}

	public Character getZeroReferenceVertex() {
		List<Vertex> zrl = v.values().stream().filter(v -> !v.hasReferents())
				.sorted(Comparator.comparing(Vertex::getIdentifier)).collect(Collectors.toList());

		return (zrl != null && !zrl.isEmpty()) ? zrl.iterator().next().getIdentifier() : null;
	}

	public boolean popVertexByIdentifier(Character identifier) {
		if (identifier == null)
			return false;

		Vertex vertex = v.get(identifier);
		if (vertex == null)
			return false;

		// evaluate the neighbors in the adjacency listing,
		// deducting one from the referent
		adj.get(vertex).stream().forEach(v -> v.removeReference());
		// now remove the vertex ref from the adjacency map
		adj.remove(vertex);
		// finally, remove the reference from the vertex map
		v.remove(vertex.getIdentifier());

		return true;
	}

	public int getVertices() {
		return v.size();
	}

	public int getIndegreeByIdentifier(Character c) {
		return v.containsKey(c) ? v.get(c).getIndegree() : 0;
	}

	class Vertex {
		private final Character identifier;
		private int indegree = 0;

		public Vertex(Character identifier) {
			this.identifier = identifier;
		}

		public Character getIdentifier() {
			return identifier;
		}

		public void addReference() {
			indegree++;
		}

		public void removeReference() {
			indegree--;
		}

		public boolean hasReferents() {
			return indegree > 0;
		}

		public int getIndegree() {
			return indegree;
		}

		@Override
		public String toString() {
			return "Vertex [" + getIdentifier() + "] has an indegree value of [" + indegree + "].";
		}
	}
}
