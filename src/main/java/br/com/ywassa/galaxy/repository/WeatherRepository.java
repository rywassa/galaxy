package br.com.ywassa.galaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ywassa.galaxy.domain.Galaxy;
import br.com.ywassa.galaxy.domain.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
	Weather findByGalaxyAndDay(final Galaxy galaxy, final Long day);
}
