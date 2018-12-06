package io.datura.java.quizzes.advent2018.day05;

import java.util.HashSet;
import java.util.Set;

public class CreateCharacterPairs {
	private static final int DELTA = 32;

	public static void main(String[] args) {
		// the 'reactive' polymers are defined as being
		// an ASCII character of the same letter
		// but in the opposite case. there's plenty
		// of tricks one can use for this, but the
		// obvious (naive?) one involves just pre-computing
		// the combinations
		
		// 97 = 'a', 122 = 'z'
		StringBuilder combo = new StringBuilder();
		for( int i = 97; i < 123; i++ ) {
			char lc = (char)i;
			char uc = (char)(i - DELTA);
			
			// Aa
			combo.append("polymerPairs.add(\"");
			combo.append(uc);
			combo.append(lc);
			combo.append("\");");
			combo.append("\n");
			
			// aA
			combo.append("polymerPairs.add(\"");
			combo.append(lc);
			combo.append(uc);
			combo.append("\");");
			combo.append("\n");
		}
		
		System.out.println(combo.toString());
		createSet();
	}

	private static void createSet() {
		Set<String> polymerPairs = new HashSet<>(64);
		polymerPairs.add("Aa");
		polymerPairs.add("aA");
		polymerPairs.add("Bb");
		polymerPairs.add("bB");
		polymerPairs.add("Cc");
		polymerPairs.add("cC");
		polymerPairs.add("Dd");
		polymerPairs.add("dD");
		polymerPairs.add("Ee");
		polymerPairs.add("eE");
		polymerPairs.add("Ff");
		polymerPairs.add("fF");
		polymerPairs.add("Gg");
		polymerPairs.add("gG");
		polymerPairs.add("Hh");
		polymerPairs.add("hH");
		polymerPairs.add("Ii");
		polymerPairs.add("iI");
		polymerPairs.add("Jj");
		polymerPairs.add("jJ");
		polymerPairs.add("Kk");
		polymerPairs.add("kK");
		polymerPairs.add("Ll");
		polymerPairs.add("lL");
		polymerPairs.add("Mm");
		polymerPairs.add("mM");
		polymerPairs.add("Nn");
		polymerPairs.add("nN");
		polymerPairs.add("Oo");
		polymerPairs.add("oO");
		polymerPairs.add("Pp");
		polymerPairs.add("pP");
		polymerPairs.add("Qq");
		polymerPairs.add("qQ");
		polymerPairs.add("Rr");
		polymerPairs.add("rR");
		polymerPairs.add("Ss");
		polymerPairs.add("sS");
		polymerPairs.add("Tt");
		polymerPairs.add("tT");
		polymerPairs.add("Uu");
		polymerPairs.add("uU");
		polymerPairs.add("Vv");
		polymerPairs.add("vV");
		polymerPairs.add("Ww");
		polymerPairs.add("wW");
		polymerPairs.add("Xx");
		polymerPairs.add("xX");
		polymerPairs.add("Yy");
		polymerPairs.add("yY");
		polymerPairs.add("Zz");
		polymerPairs.add("zZ");
		
		System.out.println(polymerPairs.size());
	}
}
