package io.datura.java.quizzes.advent2018.day04;

import java.time.LocalDateTime;
import java.util.Comparator;

import org.apache.commons.lang3.tuple.Pair;

public class GuardIntelComparator implements Comparator<Pair<LocalDateTime, String>>{

	@Override
	public int compare(Pair<LocalDateTime, String> o1, Pair<LocalDateTime, String> o2) {
		return o1.getLeft().compareTo(o2.getLeft());
	}
}
