package io.datura.java.quizzes.advent2018;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.datura.java.quizzes.advent2018.day08.LicenseReader;
import io.datura.java.quizzes.advent2018.day08.Node;

public class AdventDay08Test {
	@Test
	public void testMetaDataSum() {
		Node n = new Node();
		n.getHeader().setNumChildren(0);
		n.addMetaData(1);
		n.addMetaData(3);
		n.addMetaData(5);
		// there's no children in this case,
		// so call the sum on this one node
		// specifically
		int sum = n.getMetaData().sumMetaData();
		assertEquals(9, sum);
	}

	@Test
	public void testMetaDataSumWithChildren() {
		Node parent = new Node();
		parent.getHeader().setNumChildren(2);

		Node child = new Node();
		child.getHeader().setNumChildren(0);
		child.addMetaData(Integer.valueOf(1));
		child.addMetaData(Integer.valueOf(6));
		// 7
		parent.addChild(child);

		Node child2 = new Node();
		child.getHeader().setNumChildren(0);
		child.addMetaData(Integer.valueOf(10));
		child.addMetaData(Integer.valueOf(1));
		// 11
		parent.addChild(child2);

		// 12
		parent.addMetaData(3);
		parent.addMetaData(9);

		// 12 + 11 + 7 = 30
		int sum = LicenseReader.sumMetaData(parent);
		assertEquals(30, sum);
	}

	@Test
	public void processExample() {
		String exampleLicense = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
		Node rootNode = LicenseReader.createNodeFromLicenseString(exampleLicense);
		int sum = LicenseReader.sumMetaData(rootNode);
		assertEquals(138, sum);
	}

	@Test
	public void testAlternateMetaDataSum() {
		Node parent = new Node();
		parent.getHeader().setNumChildren(3);

		// 6
		Node child1 = new Node();
		child1.getHeader().setNumChildren(0);
		child1.addMetaData(1);
		child1.addMetaData(5);
		parent.addChild(child1);

		// 7
		Node child2 = new Node();
		child1.getHeader().setNumChildren(0);
		child2.addMetaData(5);
		child2.addMetaData(2);
		parent.addChild(child2);

		// 4
		Node child3 = new Node();
		child1.getHeader().setNumChildren(0);
		child3.addMetaData(1);
		child3.addMetaData(3);
		parent.addChild(child3);

		// we'll make the parent refer to children 1 and 3
		// so the value defined in child2 shouldn't apply
		parent.addMetaData(1);
		parent.addMetaData(3);

		int altSum = LicenseReader.altSumMetaData(parent);
		assertEquals(10, altSum);
	}

	@Test
	public void testAlternateExample() {
		String exampleLicense = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
		Node rootNode = LicenseReader.createNodeFromLicenseString(exampleLicense);
		int altSum = LicenseReader.altSumMetaData(rootNode);
		assertEquals(66, altSum);
	}
}
