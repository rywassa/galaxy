package br.com.ywassa.galaxy.geometric;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleComparatorTest {

	@Test
	public void shouldEquals() {
		assertTrue(DoubleComparator.equals(0.000001, 0.0000002));
		assertTrue(DoubleComparator.equals(0.0001, 0.0001));
	}
}