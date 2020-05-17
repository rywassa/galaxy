package br.com.ywassa.galaxy.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Galaxy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(max = 255)
	private String name;

	@NotNull
	private Long day;

	@NotNull
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "planet_one_id")
	private Planet planetOne;

	@NotNull
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "planet_two_id")
	private Planet planetTwo;

	@NotNull
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "planet_three_id")
	private Planet planetThree;
}
