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
import it.uniroma3.siw.service.AmbienteService;
import it.uniroma3.siw.service.SpecieService;
import it.uniroma3.siw.validator.SpecieValidator;
import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Specie;
import jakarta.validation.Valid;


@Controller
public class SpecieController{
	
	@Autowired SpecieValidator specieValidator;
	
	@Autowired SpecieService specieService;
	@Autowired AmbienteService ambienteService;
	
	@PostMapping("/dipendente/addSpecie")
	public String addSpecie(@Valid @ModelAttribute("specie") Specie specie,BindingResult bindingResult, Model model){
		this.specieValidator.validate(specie, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.specieService.saveSpecie(specie);
		    model.addAttribute("specie", specie);
		    return "dipendente/specieDipendente.html";
		} else {
			return "dipendente/formAddSpecie.html";
		}
		
	}   

	@GetMapping("/operazioneAddSpecie/{idSpecie}/{idAmbiente}")
	public String operazioneAddSpecieAmbiente( @PathVariable("idSpecie") Long idSpecie,@PathVariable("idAmbiente") Long idAmbiente,Model model ){
		Specie specie=this.specieService.addSpecieToAmbiente(idAmbiente,idSpecie);
		model.addAttribute("specie", specie);
		return "responsabile/indexResponsabile.html";
	}  
	
	@GetMapping("/specie/{id}")
	public String ambiente(@PathVariable("id") Long id,Model model) {
		model.addAttribute("specie",this.specieService.getSpecie(id));
		model.addAttribute("animali",this.specieService.getAnimaliSpecie(id));
		return "specie.html";
	}
	
	@GetMapping("/dipendente/specie/{id}")
	public String ambienteDip(@PathVariable("id") Long id,Model model) {
		model.addAttribute("specie",this.specieService.getSpecie(id));
		model.addAttribute("animali",this.specieService.getAnimaliSpecie(id));
		return "dipendente/specieDipendente.html";
	}
	
	@GetMapping("/dipendente/formAddSpecie")
	public String formAddAmbiente(Model model) {
		model.addAttribute("specie", new Specie());
		return "dipendente/formAddSpecie.html";
	}
	@GetMapping("dipendente/addSpecie/{idAmbiente}")
	public String AddSpecieAmbiente(@PathVariable("idAmbiente") Long id,Model model) {
		model.addAttribute("elencoSpecie",this.specieService.getAll());
		model.addAttribute("ambiente",this.ambienteService.getAmbiente(id));
		return "dipendente/addSpecieAmbiente.html";
	}
	
	@GetMapping("/elencoSpecie")
	public String elencoSpecie(Model model) {
		model.addAttribute("elencoSpecie",this.specieService.getAll());
		return "elencoSpecie.html";
	}
	
	@GetMapping("/dipendente/elencoSpecieDipendente")
	public String elencoSpecieDipendente(Model model) {
		model.addAttribute("elencoSpecie",this.specieService.getAll());
		return "dipendente/elencoSpecieDipendente.html";
	}
	
	@PostMapping("/elencoSpecie")
	public String trovaSpecie(Model model, @RequestParam String provenienza) {
		model.addAttribute("elencoSpecie", this.specieService.findByProvenienza(provenienza));
		return "elencoSpecie.html";
	}
	
	@PostMapping("/dipendente/elencoSpecie")
	public String trovaSpecieDip(Model model, @RequestParam String provenienza) {
		model.addAttribute("elencoSpecie", this.specieService.findByProvenienza(provenienza));
		return "dipendente/elencoSpecieDipendente.html";
	}
}