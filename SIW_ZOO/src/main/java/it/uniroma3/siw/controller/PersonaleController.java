package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.PersonaleRepository;


@Controller
public class PersonaleController{
	
	@Autowired PersonaleRepository personaleRepository;
	
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
	public String newDipendente(@ModelAttribute("dipendente") Personale personale, Model model) {
		if (!personaleRepository.existsByNameAndSurname(personale.getName(), personale.getSurname())) {
			this.personaleRepository.save(personale); 
			model.addAttribute("dipendente", personale);
			return "dipendente.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo dipendente esiste già");
			return "formAddDipendente.html"; 
		}
	}
}