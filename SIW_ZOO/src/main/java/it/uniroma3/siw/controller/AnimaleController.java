package it.uniroma3.siw.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Animale;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.AmbienteRepository;
import it.uniroma3.siw.repository.AnimaleRepository;
import it.uniroma3.siw.repository.SpecieRepository;

@Controller
public class AnimaleController {

	@Autowired AnimaleRepository animaleRepository;
	@Autowired SpecieRepository specieRepository;
	
	@GetMapping("/animali/{id}")
	public String animale(Model model, @PathVariable("id") Long id) 
	{
		model.addAttribute("animale",this.animaleRepository.findById(id).get());
		return "animale.html";
	}
	
	@GetMapping("/formAddAnimale/{id}")
	public String formAddAnimale(Model model,@PathVariable("id") Long id)
	{
		Specie specie= this.specieRepository.findById(id).get();
		Animale animale= new Animale();
		model.addAttribute("animale",animale);
		model.addAttribute("specie",specie);
		specie.getAnimali().add(animale);
		this.specieRepository.save(specie);
		return "formAddAnimale.html";
	}
	@PostMapping("/animali/{id}")
	public String addAnimaleSpecie( @ModelAttribute("animale") Animale animale,Model model, @PathVariable("id") Long id){
		Specie specie = this.specieRepository.findById(id).get();
		animale.setAnimal_specie(specie);
		this.animaleRepository.save(animale);
		model.addAttribute("animale", animale);
		return "animale.html";
	}   
	@PostMapping("/animali")
	public String trovaAnimali(Model model, @RequestParam int min,@RequestParam int max) {
		model.addAttribute("animali", this.animaleRepository.findByPesoInKgGreaterThanAndPesoInKgLessThan(min,max));
		return "animali.html";
	}
}
