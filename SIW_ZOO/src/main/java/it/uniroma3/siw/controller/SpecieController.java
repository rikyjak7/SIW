package it.uniroma3.siw.controller;

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
import it.uniroma3.siw.validator.SpecieValidator;
import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import jakarta.validation.Valid;


@Controller
public class SpecieController{
	
	@Autowired SpecieRepository specieRepository;
	@Autowired AmbienteRepository ambienteRepository;
	@Autowired SpecieValidator specieValidator;
	
	@PostMapping("/specie")
	public String addSpecie(@Valid @ModelAttribute("specie") Specie specie,BindingResult bindingResult, Model model){
		this.specieValidator.validate(specie, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.specieRepository.save(specie);
		    model.addAttribute("specie", specie);
		    return "specie.html";
		} else {
			return "formAddSpecie.html";
		}
		
	}   

	@GetMapping("/operazioneAddSpecie/{idSpecie}/{idAmbiente}")
	public String operazioneAddSpecieAmbiente( @PathVariable("idSpecie") Long idSpecie,@PathVariable("idAmbiente") Long idAmbiente,Model model ){
		Ambiente ambiente = this.ambienteRepository.findById(idAmbiente).get();
		Specie specie= this.specieRepository.findById(idSpecie).get();
		specie.setAmbienteOspitante(ambiente);
		ambiente.getSpecieOspitate().add(specie);
		this.specieRepository.save(specie);
		this.ambienteRepository.save(ambiente);
		model.addAttribute("specie", specie);
		return "Index.html";
	}   
	@GetMapping("/specie/{id}")
	public String ambiente(@PathVariable("id") Long id,Model model) {
		model.addAttribute("specie",this.specieRepository.findById(id).get());
		model.addAttribute("animali",this.specieRepository.findById(id).get().getAnimali());
		return "specie.html";
	}
	
	@GetMapping("/formAddSpecie")
	public String formAddAmbiente(Model model) {
		model.addAttribute("specie", new Specie());
		return "formAddSpecie.html";
	}
	@GetMapping("/addSpecie/{idAmbiente}")
	public String formAddSpecieAmbiente(@PathVariable("idAmbiente") Long id,Model model) {
		model.addAttribute("elencoSpecie",this.specieRepository.findAll());
		model.addAttribute("ambiente",this.ambienteRepository.findById(id).get());
		return "addSpecieAmbiente.html";
	}
	
	@GetMapping("/elencoSpecie")
	public String elencoSpecie(Model model) {
		model.addAttribute("elencoSpecie",this.specieRepository.findAll());
		return "elencoSpecie.html";
	}
}