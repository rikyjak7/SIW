package it.uniroma3.siw.controller;

import java.io.IOException;
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
public class AmbienteController {

	@Autowired
	AmbienteService ambienteService;
	@Autowired
	AmbienteValidator ambienteValidator;

	@GetMapping("/ambienti")
	public String ambienti(Model model) {
		model.addAttribute("ambienti", this.ambienteService.getAmbienti());
		return "ambienti.html";
	}

	@GetMapping("/dipendente/ambientiDip")
	public String ambientiResponsabile(Model model) {
		model.addAttribute("ambienti", this.ambienteService.getAmbienti());
		return "dipendente/ambientiDip.html";
	}

	@PostMapping("/ambienti")
	public String addAmbiente(@Valid @ModelAttribute("ambiente") Ambiente ambiente, BindingResult bindingResult,
			Model model) {
		try {
			model.addAttribute("ambiente", this.ambienteService.save(ambiente, bindingResult));
			model.addAttribute("elencoSpecie", new LinkedList<Specie>());
			return "dipendente/ambienteDip.html";
		} catch (IOException e) {
			return "dipendente/formAddAmbiente.html";
		}
	}

	@GetMapping("/dipendente/editAmbiente/{ambienteId}")
	public String formEditAmbiente(@PathVariable("ambienteId") Long ambienteId, Model model) {
		model.addAttribute("ambiente", this.ambienteService.getAmbiente(ambienteId));
		return "dipendente/editAmbiente.html";
	}

	@PostMapping("/dipendente/editAmbiente/{ambienteId}")
	public String editAmbiente(@Valid @ModelAttribute("ambiente") Ambiente newAmbiente,
			@PathVariable("ambienteId") Long ambienteId,
			BindingResult bindingResult, Model model) {
		model.addAttribute("elencoSpecie", this.ambienteService.getSpecieAmbiente(ambienteId));
		try {
			model.addAttribute("ambiente", this.ambienteService.saveEdit(newAmbiente, ambienteId, bindingResult));
			return "dipendente/ambienteDip.html";
		} catch (IOException e) {
			return "dipendente/editAmbiente.html";
		}
	}

	@GetMapping("/ambienti/{id}")
	public String ambiente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ambiente", this.ambienteService.getAmbiente(id));
		model.addAttribute("elencoSpecie", this.ambienteService.getSpecieAmbiente(id));
		return "ambiente.html";
	}

	@GetMapping("/dipendente/ambientiDip/{id}")
	public String ambienteDip(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ambiente", this.ambienteService.getAmbiente(id));
		model.addAttribute("elencoSpecie", this.ambienteService.getSpecieAmbiente(id));
		return "dipendente/ambienteDip.html";
	}

	@GetMapping("/dipendente/formAddAmbiente")
	public String formAddAmbiente(Model model) {
		model.addAttribute("ambiente", new Ambiente());
		return "dipendente/formAddAmbiente.html";
	}
	
	@GetMapping("/dipendente/removeAmbiente/{id}")
	public String removeAmbiente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ambienti", this.ambienteService.removeAmbienteAndReturnAll(id));
		return "dipendente/ambientiDip.html";
	}
}