package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.model.Animale;
import it.uniroma3.siw.repository.AmbienteRepository;
import it.uniroma3.siw.repository.AnimaleRepository;
import it.uniroma3.siw.repository.SpecieRepository;
import jakarta.validation.Valid;


@Service
public class SpecieService {
	@Autowired SpecieRepository specieRepository;
	@Autowired AmbienteRepository ambienteRepository;
	@Autowired AnimaleRepository animaleRepository;
	
	public void saveSpecie(@Valid Specie specie) {
		this.specieRepository.save(specie);
	}
	public Specie addSpecieToAmbiente(Long idAmbiente, Long idSpecie) {
		Ambiente ambiente = this.ambienteRepository.findById(idAmbiente).get();
		Specie specie= this.specieRepository.findById(idSpecie).get();
		specie.setAmbienteOspitante(ambiente);
		ambiente.getSpecieOspitate().add(specie);
		this.specieRepository.save(specie);
		this.ambienteRepository.save(ambiente);
		return specie;
	}
	public Specie getSpecie(Long id) {
		
		return this.specieRepository.findById(id).get();
	}
public List<Animale> getAnimaliSpecie(Long id) {
		
		return this.specieRepository.findById(id).get().getAnimali();
	}
public Iterable<Specie> getAll() {
	return this.specieRepository.findAll();
}
public Object findByProvenienza(String provenienza) {
	return this.specieRepository.findByProvenienza(provenienza);
}
public void addAnimale(Animale newAnimale,Long id) {
	Specie specie = this.specieRepository.findById(id).get();
	newAnimale.setAnimal_specie(specie);
	this.animaleRepository.save(newAnimale);
	}


}
