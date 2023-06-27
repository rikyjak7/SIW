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
import it.uniroma3.siw.service.AnimaleService;
import it.uniroma3.siw.service.SpecieService;

@Controller
public class AnimaleController {

	@Autowired AnimaleService animaleService;
	@Autowired SpecieService specieService;
	
	@GetMapping("/animali/{id}")
	public String animale(Model model, @PathVariable("id") Long id) 
	{
		model.addAttribute("animale",this.animaleService.getAnimale(id));
		return "animale.html";
	}
	
	@GetMapping("/dipendente/formAddAnimale/{id}")
	public String formAddAnimale(Model model,@PathVariable("id") Long id)
	{
		Specie specie= this.specieService.getSpecie(id);
		Animale animale= new Animale();
		model.addAttribute("animale",animale);
		model.addAttribute("specie",specie);
		specie.getAnimali().add(animale);
		this.specieService.saveSpecie(specie);
		return "dipendente/formAddAnimale.html";
	}
	@PostMapping("/animali/{id}")
	public String addAnimaleSpecie( @ModelAttribute("animale") Animale animale,Model model, @PathVariable("id") Long id){
		Animale newAnimale= new Animale(animale.getNome(),animale.getFoto(),animale.getEta(),animale.getDescrizione(),animale.getPesoInKg());
		specieService.addAnimale(newAnimale,id);
		model.addAttribute("animale", newAnimale);
		return "animale.html";
	}   
	@PostMapping("/animali")
	public String trovaAnimali(Model model, @RequestParam int min,@RequestParam int max) {
		model.addAttribute("animali", this.animaleService.getRangeAnimali(min,max));
		return "animali.html";
	}
}
