package br.com.ywassa.galaxy.geometric;

import java.awt.geom.Path2D;
import java.io.Serializable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Triangle implements Serializable {
	private Point a;
	private Point b;
	private Point c;

	public boolean contains(final Point point) {
		final Path2D.Double path = new Path2D.Double();
		path.moveTo(a.getX(), a.getY());
		path.lineTo(b.getX(), b.getY());
		path.lineTo(c.getX(), c.getY());
		path.closePath();

		return path.contains(point.getX(), point.getY());
	}

	public double perimeter() {
		return perimeter(a, b) + perimeter(b, c) + perimeter(c, a);
	}

	private double perimeter(final Point point1, final Point point2) {
		final double x1 = point1.getX();
		final double x2 = point2.getX();
		final double y1 = point1.getY();
		final double y2 = point2.getY();
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}
