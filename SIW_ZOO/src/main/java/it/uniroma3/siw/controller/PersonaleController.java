package it.uniroma3.siw.controller;

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
	public String staff(Model model) {
		return "staff.html";
	}
	
	@GetMapping("/personale")
	public String showDipendenti(Model model) {
		model.addAttribute("personale", this.personaleRepository.findAll());
		return "staff.html";
	}
	
	@GetMapping("/personale/{id}")
	public String getDipendente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("personale", this.personaleRepository.findById(id).get());
		return "dipendente.html";
	}
	
	@GetMapping("/formAddDipendente")
	public String formAddDipendente(Model model) {
		model.addAttribute("personale", new Personale());
		return "formAddDipendente.html";
	}
}