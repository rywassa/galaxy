package br.com.ywassa.galaxy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ywassa.galaxy.domain.Galaxy;

public interface GalaxyRepository extends JpaRepository<Galaxy, Integer> {
}
