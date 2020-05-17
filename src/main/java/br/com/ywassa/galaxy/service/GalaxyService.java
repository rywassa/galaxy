package br.com.ywassa.galaxy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ywassa.galaxy.domain.Galaxy;
import br.com.ywassa.galaxy.repository.GalaxyRepository;

@Service
public class GalaxyService {

	@Autowired
	private GalaxyRepository galaxyRepository;

	public Galaxy findById(final Integer id) {
		return galaxyRepository.getOne(id);
	}

	public Galaxy save(final Galaxy galaxy) {
		return galaxyRepository.save(galaxy);
	}
}
