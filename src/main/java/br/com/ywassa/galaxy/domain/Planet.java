package br.com.ywassa.galaxy.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Planet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 255)
	private String name;

	@NotNull
	private BigDecimal angularSpeedInDegreeByDay;

	private boolean clockwise;

	@NotNull
	private BigDecimal distanceInKm;

	@NotNull
	private BigDecimal lastAngleInRad;

	protected Planet() {
	}

	public Planet(final Long id,
				  final String name,
				  final double angularSpeedInDegreeByDay,
				  final boolean clockwise,
				  final double distanceInKm,
				  final BigDecimal lastAngleInRad) {
		this.id = id;
		this.name = name;
		this.angularSpeedInDegreeByDay = BigDecimal.valueOf(angularSpeedInDegreeByDay);
		this.clockwise = clockwise;
		this.distanceInKm = BigDecimal.valueOf(distanceInKm);
		this.lastAngleInRad = lastAngleInRad;
	}
}
