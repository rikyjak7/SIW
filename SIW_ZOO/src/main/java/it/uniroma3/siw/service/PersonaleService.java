package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.repository.PersonaleRepository;
import it.uniroma3.siw.validator.PersonaleValidator;
import jakarta.validation.Valid;

@Service
public class PersonaleService {
	@Autowired
	PersonaleRepository personaleRepository;

	@Autowired
	PersonaleValidator personaleValidator;

	@Transactional
	public Iterable<Personale> getDipendenti() {
		return this.personaleRepository.findAll();
	}

	@Transactional
	public List<Personale> getResponsabili() {
		return this.personaleRepository.findByIsResponsabile(true);
	}

	@Transactional
	public Personale getDipendente(Long id) {
		return this.personaleRepository.findById(id).get();
	}

	@Transactional
	public Personale save(Personale personale, BindingResult bindingResult, MultipartFile image) throws IOException {
		String base64Image; 
		this.personaleValidator.validate(personale, bindingResult);
		if (!bindingResult.hasErrors()) {
			try {
			base64Image = Base64.getEncoder().encodeToString(image.getBytes());
			personale.setImage(base64Image);	
			} catch (IOException e) {
				// TODO: handle exception
			}
			return this.personaleRepository.save(personale);
		} else {
			throw new IOException();
		}
	}

	@Transactional
	public Personale saveEdit(Personale newDipendente, Long id, BindingResult bindingResult) throws IOException {

		this.personaleValidator.validate(newDipendente, bindingResult);
		if (!bindingResult.hasFieldErrors()) {
			Personale dipendente = this.personaleRepository.findById(id).get();
			dipendente.setName(newDipendente.getName());
			dipendente.setSurname(newDipendente.getSurname());
			dipendente.setAge(newDipendente.getAge());
			dipendente.setImage(newDipendente.getImage());
			dipendente.setStipendio(newDipendente.getStipendio());
			dipendente.setIsResponsabile(newDipendente.getIsResponsabile());
			return this.personaleRepository.save(dipendente);
		} else {
			throw new IOException();
		}
	}

	@Transactional
	public void removeDip(Long id, Model model) throws IOException {
		
		if (!(this.personaleRepository.findById(id).get().getIsResponsabile() == true)) {
			this.personaleRepository.deleteById(id);
		} else {
			throw new IOException();
		}
	}

	@Transactional
	public Iterable<Personale> getAllResponsabili() {
		return this.personaleRepository.findByIsResponsabile(true);
	}

	@Transactional
	public void modificaStipendio(Personale personale, float stipendio) {
		personale.setStipendio(stipendio);
		this.personaleRepository.save(personale);
	}
	@Transactional
    public void rimuoviResp(Personale dipendente) {
		if(dipendente.getIsResponsabile() == true)
		{
			dipendente.getAmbienteControllato().setResponsabile(null);
			dipendente.setIsResponsabile(false);
		}
	}
}
