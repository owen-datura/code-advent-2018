package io.datura.java.quizzes.advent2018.day06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationMatrix {
	// using a map is a tradeoff here: the requirements demand that
	// the representation go on indefinitely (so future expansion
	// may be necessary), but an ArrayList doesn't have a good
	// upsert/'test insert @ index' operation that doesn't involve
	// a try/catch wrap or pre-filling its contents (gross)
	//
	// realistically a better solution would be a sparse array,
	// but that's not a standard class
	private final List<Map<Integer, Destination>> matrix;

	private DestinationMatrix() {
		this.matrix = new ArrayList<>();
	}

	public static DestinationMatrix create(Collection<Destination> destinations) {
		DestinationMatrix m = new DestinationMatrix();
		for (Destination d : destinations) {
			m.addDestination(d);
		}
		return m;
	}

	private void addDestination(Destination d) {
		int rows = matrix.size();
		if (rows < d.getY()) {
			int toAdd = d.getY() - rows;
			// less than-equals here because there exists a 'row 0'
			for (int i = 0; i <= toAdd; i++) {
				matrix.add(new HashMap<>());
			}
		}

		Map<Integer, Destination> row = matrix.get(d.getY());
		row.put(d.getX(), d);
	}

	public int getMaxColumn() {
		int max = 0;
		for (Map<Integer, Destination> row : matrix) {
			// the #max call will generate an exception with an empty collection
			if (row.isEmpty())
				continue;

			max = Math.max(max, Collections.max(row.keySet()));
		}
		return max;
	}

	public void printMatrix() {
		int maxCol = getMaxColumn();
		
		StringBuilder output = new StringBuilder();
		for (Map<Integer, Destination> row : matrix) {
			for( int i = 0; i < maxCol; i++ ) {
				output.append(row.containsKey(i) ? row.get(i).getIdentifier() : ".");
			}
			output.append("\n");
		}
		
		System.out.println(output.toString());
	}
}
