package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.AmbienteRepository;
import it.uniroma3.siw.repository.PersonaleRepository;
import jakarta.validation.Valid;

@Service
public class AmbienteService {

	@Autowired AmbienteRepository ambienteRepository;
	@Autowired PersonaleRepository personaleRepository;
	
	public List<Specie> getSpecieAmbiente(Long id) {
		return this.ambienteRepository.findById(id).get().getSpecieOspitate();
	}
	public Ambiente getAmbiente(Long id) {
		return this.ambienteRepository.findById(id).get();
	}
	public Iterable<Ambiente> getAmbienti() {
		return this.ambienteRepository.findAll();
	}
	public void save(@Valid Ambiente ambiente) {
		this.ambienteRepository.save(ambiente);
	}
	public void saveResponsabile(Long idAmbiente, Long idResponsabile) {
		Ambiente ambiente = this.ambienteRepository.findById(idAmbiente).get();
		Personale responsabile= this.personaleRepository.findById(idResponsabile).get();
		ambiente.setResponsabile(responsabile);
		responsabile.setIsResponsabile(true);
		this.personaleRepository.save(responsabile);
		this.ambienteRepository.save(ambiente);
	}
	
}
