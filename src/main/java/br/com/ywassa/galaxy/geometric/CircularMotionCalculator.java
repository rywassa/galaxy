package br.com.ywassa.galaxy.geometric;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CircularMotionCalculator {

	public static double newAngle(final double angularSpeed, final double angleBefore) {
		//Formula simplificada: w = delta Teta / delta t
		return angularSpeed + angleBefore;
	}

	/**
	 * Unidade Rad/dia
	 * @param angularSpeed
	 * @return
	 */
	public static double convertAngularSpeedInDegreeByDayToRadByDay(final double angularSpeed) {
		return Math.toRadians(angularSpeed);
	}
}
