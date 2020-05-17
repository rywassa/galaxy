package br.com.ywassa.galaxy.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long day;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Forecast forecast;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "galaxy_id")
	private Galaxy galaxy;

	Weather() {
	}

	public Weather(final Long day, final Forecast forecast, final Galaxy galaxy) {
		this.day = day;
		this.forecast = forecast;
		this.galaxy = galaxy;
	}
}
