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

}
