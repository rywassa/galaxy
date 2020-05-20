package br.com.ywassa.galaxy.geometric;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DoubleComparator {

	private static final double EPSILON = 0.000001;

	public static boolean equals(final double value1, final double value2) {
		return value1 == value2 ? true : Math.abs(value1 - value2) < EPSILON;
	}

}
