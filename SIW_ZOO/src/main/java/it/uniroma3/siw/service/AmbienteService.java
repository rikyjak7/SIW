package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.AmbienteRepository;

@Service
public class AmbienteService {

	@Autowired AmbienteRepository ambienteRepository;
	
	public List<Specie> getSpecieAmbiente(Long id) {
		return this.ambienteRepository.findById(id).get().getSpecieOspitate();
	}
	public Ambiente getAmbiente(Long id) {
		return this.ambienteRepository.findById(id).get();
	}
	
}
