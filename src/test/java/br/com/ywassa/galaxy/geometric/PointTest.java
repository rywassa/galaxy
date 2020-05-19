package br.com.ywassa.galaxy.geometric;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void shouldCreatePointAt1and0() {
		final Point point = Point.of(0.0, 1);
		assertTrue(DoubleComparator.equals(point.getX(), 1.0));
		assertTrue(DoubleComparator.equals(point.getY(), 0.0));
	}

	@Test
	public void shouldCreatePointAt1and1() {
		final Point point = Point.of(Math.PI / 2, 1);
		assertTrue(DoubleComparator.equals(point.getX(), 0.0));
		assertTrue(DoubleComparator.equals(point.getY(), 1.0));
	}

	@Test
	public void shouldCreatePointAtMinus1and0() {
		final Point point = Point.of(Math.PI, 1);
		assertTrue(DoubleComparator.equals(point.getX(), -1.0));
		assertTrue(DoubleComparator.equals(point.getY(), 0.0));
	}

	@Test
	public void shouldCreatePointAtMinus0and1() {
		final Point point = Point.of((3 * Math.PI) / 2, 1);
		assertTrue(DoubleComparator.equals(point.getX(), 0.0));
		assertTrue(DoubleComparator.equals(point.getY(), -1.0));
	}

	@Test
	public void shouldCreatePointAtMinus1and1() {
		final Point point = Point.of((3 * Math.PI) / 2, 1);
		assertTrue(DoubleComparator.equals(point.getX(), 0.0));
		assertTrue(DoubleComparator.equals(point.getY(), -1.0));
	}
}