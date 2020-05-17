package br.com.ywassa.galaxy.geometric;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Point implements Serializable {
	private double x;
	private double y;

	public static Point of(final double angle, final double radius) {
		return new Point(radius * Math.cos(angle), radius * Math.sin(angle));
	}
}