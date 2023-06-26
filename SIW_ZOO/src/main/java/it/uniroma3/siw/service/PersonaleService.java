package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.repository.PersonaleRepository;
import jakarta.validation.Valid;

@Service
public class PersonaleService {
@Autowired PersonaleRepository personaleRepository;

public Iterable<Personale> getDipendenti() {
	return this.personaleRepository.findAll();
}

public List<Personale> getResponsabili() {
	return this.personaleRepository.findByIsResponsabile(true);
}

public Personale getDipendente(Long id) {
	return this.personaleRepository.findById(id).get();
}

public void save(@Valid Personale personale) {
	this.personaleRepository.save(personale);
}

public void modificaStipendio(Personale personale, float stipendio) {
	personale.setStipendio(stipendio);
	this.personaleRepository.save(personale);
}

}
