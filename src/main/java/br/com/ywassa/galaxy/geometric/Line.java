package br.com.ywassa.galaxy.geometric;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Line implements Serializable {
	private Point a;
	private Point b;

	public boolean isAligned(final Point c) {
		final double value = (a.getX() * b.getY() * 1 + a.getY() * 1 * c.getX() + 1 * b.getX() * c.getY()) -
				(1 * b.getY() * c.getX() + a.getX() * 1 * c.getY() + a.getY() * b.getX() * 1);
		return DoubleComparator.equals(value, 0.0);
	}
}
