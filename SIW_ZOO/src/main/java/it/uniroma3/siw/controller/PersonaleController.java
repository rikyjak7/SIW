package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Ambiente;
import it.uniroma3.siw.model.Personale;
import it.uniroma3.siw.model.Specie;
import it.uniroma3.siw.repository.AmbienteRepository;
import it.uniroma3.siw.repository.PersonaleRepository;
import it.uniroma3.siw.service.AmbienteService;
import it.uniroma3.siw.service.PersonaleService;
import it.uniroma3.siw.validator.PersonaleValidator;
import jakarta.validation.Valid;


@Controller
public class PersonaleController{
	
	
	@Autowired PersonaleValidator personaleValidator;
	@Autowired PersonaleService personaleService;
	@Autowired AmbienteService ambienteService;
	
	@GetMapping("/loginPage")
	public String login(Model model) {
		return "login.html";
	}
	
	@GetMapping("/staff")
	public String showDipendenti(Model model) {
		
		
		List<Personale> dipendenti=(List<Personale>) this.personaleService.getDipendenti();
		List<Personale> responsabili=(List<Personale>) this.personaleService.getResponsabili();
		model.addAttribute("responsabili", responsabili);
		model.addAttribute("dipendenti", dipendenti);
		return "staff.html";
	}
	
	@GetMapping("/personale/{id}")
	public String getDipendente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		return "dipendente.html";
	}
	
	@GetMapping("/formAddDipendente")
	public String formAddDipendente(Model model) {
		model.addAttribute("dipendente", new Personale());
		return "formAddDipendente.html";
	}
	@GetMapping("/modificaStipendio/{id}")
	public String modificaStipendio(Model model, @PathVariable("id")Long id) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		return "modificaStipendio.html";
	}
	@PostMapping("/staff")
	public String newDipendente(@Valid @ModelAttribute("dipendente") Personale personale, BindingResult bindingResult, Model model) {
		this.personaleValidator.validate(personale, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.personaleService.save(personale);
			model.addAttribute("dipendente", personale);
			return "dipendente.html";
		} else {
			return "formAddDipendente.html";
		}
	}
	@GetMapping("/setAmbienteResponsabile/{idResponsabile}")
	public String addResponsabile(@PathVariable("idResponsabile") Long id,Model model)
	{	
		model.addAttribute("dipendente",this.personaleService.getDipendente(id));
		model.addAttribute("ambienti",this.ambienteService.getAmbienti());
		return "setAmbienteResponsabile.html";
	}
	@GetMapping("/operazioneAddResponsabile/{idResponsabile}/{idAmbiente}")
	public String operazioneAddResponsabileAmbiente( @PathVariable("idResponsabile") Long idResponsabile,@PathVariable("idAmbiente") Long idAmbiente,Model model ){
		this.ambienteService.saveResponsabile(idAmbiente,idResponsabile);
				return "Index.html";
	}
	@PostMapping("/dipendente/{id}")
	public String modificaStipendio( @ModelAttribute("stipendio") float stipendio,@PathVariable("id") Long id, Model model) {
			Personale personale=this.personaleService.getDipendente(id);
			this.personaleService.modificaStipendio(personale,stipendio);
			model.addAttribute("dipendente", personale);
			return "dipendente.html";
	}
}