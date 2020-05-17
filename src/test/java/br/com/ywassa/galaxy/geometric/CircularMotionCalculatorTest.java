package br.com.ywassa.galaxy.geometric;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircularMotionCalculatorTest {

	@Test
	public void shouldCalculateAngle() {
		assertTrue(DoubleComparator.equals(CircularMotionCalculator.newAngle(0, 0), 0));
	}

	@Test
	public void shouldConvert() {
		final double radBySec = CircularMotionCalculator.convertAngularSpeedInDegreeByDayToRadByDay(1);
		assertTrue(DoubleComparator.equals(radBySec, 0.017453292519943295));
	}
}