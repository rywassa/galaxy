package br.com.ywassa.galaxy.geometric;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineTest {

	@Test
	public void shouldAligned() {
		final Point a = new Point(2.0,5.0);
		final Point b = new Point(3.0,7.0);
		final Point c = new Point(5.0,11.0);

		final Line line = new Line(a, b);
		assertTrue(line.isAligned(c));
	}

	@Test
	public void shouldAlignedIn45degree() {
		final Point a = new Point(0.0,0.0);
		final Point b = new Point(1.0,1.0);
		final Point c = new Point(2.0,2.0);

		final Line line = new Line(a, b);
		assertTrue(line.isAligned(c));
	}
}