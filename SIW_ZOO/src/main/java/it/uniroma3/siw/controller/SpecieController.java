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
import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import jakarta.validation.Valid;


@Controller
public class SpecieController{
	
	@Autowired SpecieRepository specieRepository;
	@Autowired AmbienteRepository ambienteRepository;
	
	@PostMapping("/specie")
	public String addSpecie( @ModelAttribute("specie") Specie specie,Model model){
		this.specieRepository.save(specie);
		model.addAttribute("specie", specie);
		return "specie.html";
	}   

	@PostMapping("/ambienti/{id}")
	public String addSpecieAmbiente( @ModelAttribute("specie") Specie specie,Model model, @PathVariable("id") Long id){
		Ambiente ambiente = this.ambienteRepository.findById(id).get();
		specie.setAmbienteOspitante(ambiente);
		this.specieRepository.save(specie);
		model.addAttribute("specie", specie);
		return "specie.html";
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
	@GetMapping("/formAddSpecie/{idAmbiente}")	//da modificare per far scegliere all utente specie gia esistenti.
	public String formAddSpecieAmbiente(@PathVariable("idAmbiente") Long id,Model model) {
		Ambiente ambiente= this.ambienteRepository.findById(id).get();
		Specie specie= new Specie();
		model.addAttribute("specie", specie);
		model.addAttribute("ambiente", ambiente);
		ambiente.getSpecieOspitate().add(specie);
		this.ambienteRepository.save(ambiente);
		return "formAddSpecieAmbiente.html";
	}
	
	@GetMapping("/elencoSpecie")
	public String elencoSpecie(Model model) {
		model.addAttribute("elencoSpecie",this.specieRepository.findAll());
		return "elencoSpecie.html";
	}

}