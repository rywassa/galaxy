package br.com.ywassa.galaxy.geometric;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TriangleTest {

	@Test
	public void shouldContain() {
		final Point a = new Point(1.0, 1.0);
		final Point b = new Point(5.0, 5.0);
		final Point c = new Point(4.0, 1.0);
		final Point d = new Point(3.0, 2.0);

		final Triangle triangle = new Triangle(a, b, c);
		assertTrue(triangle.contains(d));
	}

	@Test
	public void shouldNotContain() {
		final Point a = new Point(1.0, 1.0);
		final Point b = new Point(5.0, 5.0);
		final Point c = new Point(4.0, 1.0);
		final Point d = new Point(3.0, 5.0);

		final Triangle triangle = new Triangle(a, b, c);
		assertFalse(triangle.contains(d));
	}

	@Test
	public void perimeter() {
		final Point a = new Point(1.0, 1.0);
		final Point b = new Point(2.0, 2.0);
		final Point c = new Point(2.0, 1.0);

		final Triangle triangle = new Triangle(a, b, c);
		final double perimeter = triangle.perimeter();
		assertTrue(DoubleComparator.equals(perimeter, 3.4142135623730951));
	}

}