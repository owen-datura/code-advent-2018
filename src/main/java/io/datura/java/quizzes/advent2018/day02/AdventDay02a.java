package io.datura.java.quizzes.advent2018.day02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdventDay02a {
	public static void main(String[] args) {

	}

	public static void evalBoxCodes(Collection<BoxCode> codes) {
		for (BoxCode code : codes) {

		}
	}

	public static List<BoxCode> stringToObj(Collection<String> codes) {
		List<BoxCode> bl = new ArrayList<>();
		for (String code : codes) {
			bl.add(new BoxCode(code));
		}
		return bl;
	}
}
