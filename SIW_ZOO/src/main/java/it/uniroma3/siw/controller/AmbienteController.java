package it.uniroma3.siw.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.repository.*;
import it.uniroma3.siw.service.AmbienteService;
import it.uniroma3.siw.validator.AmbienteValidator;
import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import jakarta.validation.Valid;


@Controller
public class AmbienteController{
	
	@Autowired AmbienteService ambienteService;
	@Autowired AmbienteValidator ambienteValidator;
	
	@GetMapping("/ambienti")
	public String login(Model model) {
		model.addAttribute("ambienti",this.ambienteService.getAmbienti());
		return "ambienti.html";
	}
	
	@PostMapping("/ambienti")
	public String addAmbiente( @Valid @ModelAttribute("ambiente") Ambiente ambiente,BindingResult bindingResult, Model model){
		this.ambienteValidator.validate(ambiente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.ambienteService.save(ambiente);
		    model.addAttribute("ambiente", ambiente);
		    model.addAttribute("elencoSpecie", new LinkedList<Specie>());
		    return "ambiente.html";
		} else {
			return "formAddAmbiente.html";
		}
	}   
	
	@GetMapping("/ambienti/{id}")
	public String ambiente(@PathVariable("id") Long id,Model model) {
		model.addAttribute("ambiente",this.ambienteService.getAmbiente(id));
		model.addAttribute("elencoSpecie",this.ambienteService.getSpecieAmbiente(id));
		return "ambiente.html";
	}
	
	@GetMapping("/dipendente/formAddAmbiente")
	public String formAddAmbiente(Model model) {
		model.addAttribute("ambiente", new Ambiente());
		return "dipendente/formAddAmbiente.html";
	}
}