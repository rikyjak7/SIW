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
import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import jakarta.validation.Valid;


@Controller
public class AmbienteController{
	
	@Autowired AmbienteRepository ambienteRepository;	
	
	@GetMapping("/ambienti")
	public String login(Model model) {
		model.addAttribute("ambienti",this.ambienteRepository.findAll());
		return "ambienti.html";
	}
	@PostMapping("/ambienti")
	public String addAmbiente( @ModelAttribute("ambiente") Ambiente ambiente,Model model){
		this.ambienteRepository.save(ambiente);
		model.addAttribute("ambiente", ambiente);
		model.addAttribute("elencoSpecie", new LinkedList<Specie>());
		return "ambiente.html";
	}   
	
	@GetMapping("/ambienti/{id}")
	public String ambiente(@PathVariable("id") Long id,Model model) {
		model.addAttribute("ambiente",this.ambienteRepository.findById(id).get());
		model.addAttribute("elencoSpecie",this.ambienteRepository.findById(id).get().getSpecieOspitate());
		return "ambiente.html";
	}
	
	@GetMapping("/formAddAmbiente")
	public String formAddAmbiente(Model model) {
		model.addAttribute("ambiente", new Ambiente());
		return "formAddAmbiente.html";
	}
}