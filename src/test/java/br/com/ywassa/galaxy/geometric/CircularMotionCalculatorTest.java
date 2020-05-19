package br.com.ywassa.galaxy.geometric;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircularMotionCalculatorTest {

	@Test
	public void shouldCalculateAngle() {
		assertTrue(DoubleComparator.equals(CircularMotionCalculator.newAngle(0, 0), 0));
		assertTrue(DoubleComparator.equals(CircularMotionCalculator.newAngle(10, 0), 10));
		assertTrue(DoubleComparator.equals(CircularMotionCalculator.newAngle(50, Math.PI), Math.PI + 50));
	}

	@Test
	public void shouldConvert() {
		final double radBySec = CircularMotionCalculator.convertAngularSpeedInDegreeByDayToRadByDay(1);
		assertTrue(DoubleComparator.equals(radBySec, 0.017453292519943295));
	}
}