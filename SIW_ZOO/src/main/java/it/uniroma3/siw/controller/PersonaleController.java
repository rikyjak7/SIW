package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.PersonaleRepository;
import it.uniroma3.siw.validator.PersonaleValidator;
import jakarta.validation.Valid;


@Controller
public class PersonaleController{
	
	@Autowired PersonaleRepository personaleRepository;
	@Autowired PersonaleValidator personaleValidator;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login.html";
	}
	
	@GetMapping("/staff")
	public String showDipendenti(Model model) {
		List<Personale> dipendenti=(List<Personale>) this.personaleRepository.findAll();
		model.addAttribute("dipendenti", dipendenti);
		return "staff.html";
	}
	
	@GetMapping("/personale/{id}")
	public String getDipendente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dipendente", this.personaleRepository.findById(id).get());
		return "dipendente.html";
	}
	
	@GetMapping("/formAddDipendente")
	public String formAddDipendente(Model model) {
		model.addAttribute("dipendente", new Personale());
		return "formAddDipendente.html";
	}
	
	@PostMapping("/staff")
	public String newDipendente(@Valid @ModelAttribute("dipendente") Personale personale, BindingResult bindingResult, Model model) {
		this.personaleValidator.validate(personale, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.personaleRepository.save(personale); 
			model.addAttribute("dipendente", personale);
			return "dipendente.html";
		} else {
			return "formAddDipendente.html";
		}
	}
}