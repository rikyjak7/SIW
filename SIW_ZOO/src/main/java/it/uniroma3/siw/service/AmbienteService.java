package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.AmbienteRepository;
import it.uniroma3.siw.repository.PersonaleRepository;
import it.uniroma3.siw.validator.AmbienteValidator;
import jakarta.validation.Valid;

@Service
public class AmbienteService {

	@Autowired AmbienteRepository ambienteRepository;
	@Autowired PersonaleRepository personaleRepository;
	
	@Autowired AmbienteValidator ambienteValidator;
	
	@Transactional
	public List<Specie> getSpecieAmbiente(Long id) {
		return this.ambienteRepository.findById(id).get().getSpecieOspitate();
	}
	
	@Transactional
	public Ambiente getAmbiente(Long id) {
		return this.ambienteRepository.findById(id).get();
	}
	
	@Transactional
	public Iterable<Ambiente> getAmbienti() {
		return this.ambienteRepository.findAll();
	}
	
	@Transactional
	public Ambiente save(Ambiente ambiente, BindingResult bindingResult) throws IOException{
		
		this.ambienteValidator.validate(ambiente, bindingResult);
		if(!bindingResult.hasErrors()) {
			return this.ambienteRepository.save(ambiente);
		} else {
			throw new IOException(); 
		}
	}
	
	@Transactional
	public Ambiente saveEdit(Ambiente newAmbiente, Long ambienteId, BindingResult bindingResult) throws IOException{
		this.ambienteValidator.validate(newAmbiente, bindingResult);
		if(!bindingResult.hasFieldErrors()) {
			Ambiente ambiente=this.ambienteRepository.findById(ambienteId).get();
			ambiente.setNome(newAmbiente.getNome());
			ambiente.setImage(newAmbiente.getImage());
			ambiente.setDescrizione(newAmbiente.getDescrizione());
			ambiente.setSuperficie(newAmbiente.getSuperficie());
			ambiente.setSpecieOspitate(newAmbiente.getSpecieOspitate());
			ambiente.setDipendente(newAmbiente.getDipendente());
			ambiente.setResponsabile(newAmbiente.getResponsabile());
			return this.ambienteRepository.save(ambiente);
		} else {
			throw new IOException(); 
		}
	}
	
	@Transactional
	public void saveResponsabile(Long idAmbiente, Long idResponsabile) {
		Ambiente ambiente = this.ambienteRepository.findById(idAmbiente).get();
		Personale responsabile= this.personaleRepository.findById(idResponsabile).get();
		ambiente.setResponsabile(responsabile);
		responsabile.setIsResponsabile(true);
		responsabile.setAmbienteControllato(ambiente);
		this.personaleRepository.save(responsabile);
		this.ambienteRepository.save(ambiente);
	}
	
}
