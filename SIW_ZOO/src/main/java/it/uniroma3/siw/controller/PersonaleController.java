package it.uniroma3.siw.controller;

import java.io.IOException;
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
import jakarta.validation.GroupSequence;
import jakarta.validation.Valid;

@Controller
public class PersonaleController {

	@Autowired
	PersonaleValidator personaleValidator;
	@Autowired
	PersonaleService personaleService;
	@Autowired
	AmbienteService ambienteService;

	@GetMapping("/loginPage")
	public String login(Model model) {
		return "login.html";
	}

	@GetMapping("/staff")
	public String showDipendenti(Model model) {

		List<Personale> dipendenti = (List<Personale>) this.personaleService.getDipendenti();
		List<Personale> responsabili = (List<Personale>) this.personaleService.getResponsabili();
		model.addAttribute("responsabili", responsabili);
		model.addAttribute("dipendenti", dipendenti);
		return "staff.html";
	}

	@GetMapping("/responsabile/staffResponsabile")
	public String showDipendentiResponsabile(Model model) {

		List<Personale> dipendenti = (List<Personale>) this.personaleService.getDipendenti();
		List<Personale> responsabili = (List<Personale>) this.personaleService.getResponsabili();
		model.addAttribute("responsabili", responsabili);
		model.addAttribute("dipendenti", dipendenti);
		return "responsabile/staffResponsabile.html";
	}

	@GetMapping("/personale/{id}")
	public String getDipendente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		return "dipendente.html";
	}

	@GetMapping("/personaleResponsabile/{id}")
	public String getDipendenteResponsabile(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		return "responsabile/dipendenteResponsabile.html";
	}

	@GetMapping("/responsabile/formAddDipendente")
	public String formAddDipendente(Model model) {
		model.addAttribute("dipendente", new Personale());
		return "responsabile/formAddDipendente.html";
	}

	@GetMapping("/responsabile/modificaStipendio/{id}")
	public String modificaStipendio(Model model, @PathVariable("id") Long id) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		return "responsabile/modificaStipendio.html";
	}

	@PostMapping("/responsabile/staffResponsabile")
	public String newDipendente(@Valid @ModelAttribute("dipendente") Personale personale, BindingResult bindingResult,
			Model model) {

		try {
			model.addAttribute("dipendente", this.personaleService.save(personale, bindingResult));
			return "responsabile/dipendenteResponsabile.html";
		} catch (IOException e) {
			return "responsabile/formAddDipendente.html";
		}
	}

	@GetMapping("/responsabile/editDipendente/{id}")
	public String formEditDipendente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		return "responsabile/editDipendente.html";
	}

	@PostMapping("/responsabile/editDipendente/{dipendenteId}")
	public String editDipendente(@Valid @ModelAttribute("dipendente") Personale newDipendente,
			@PathVariable("dipendenteId") Long dipendenteId,
			BindingResult bindingResult, Model model) {
		try {
			model.addAttribute("dipendente",
					this.personaleService.saveEdit(newDipendente, dipendenteId, bindingResult));
			return "responsabile/dipendenteResponsabile.html";
		} catch (IOException e) {
			return "responsabile/editDipendente.html";
		}
	}

	@GetMapping("/responsabile/removeDipendente/{id}")
	public String removeDipendente(@PathVariable("id") Long id, Model model) {

		try {
			this.personaleService.removeDip(id, model);
			model.addAttribute("dipendenti", this.personaleService.getDipendenti());
			model.addAttribute("responsabili", this.personaleService.getAllResponsabili());
		} catch (IOException e) {
			String errore="non puoi eliminare un responsabile!!";
			model.addAttribute("errore", errore);
			model.addAttribute("dipendente", this.personaleService.getDipendente(id));
			return "/responsabile/dipendenteResponsabile.html";
		}
		return "responsabile/staffResponsabile.html";
	}

	@GetMapping("responsabile/setAmbienteResponsabile/{idResponsabile}")
	public String addResponsabile(@PathVariable("idResponsabile") Long id, Model model) {
		model.addAttribute("dipendente", this.personaleService.getDipendente(id));
		model.addAttribute("ambienti", this.ambienteService.getAmbienti());
		return "responsabile/setAmbienteResponsabile.html";
	}

	@GetMapping("/operazioneAddResponsabile/{idResponsabile}/{idAmbiente}")
	public String operazioneAddResponsabileAmbiente(@PathVariable("idResponsabile") Long idResponsabile,
			@PathVariable("idAmbiente") Long idAmbiente, Model model) {
		this.ambienteService.saveResponsabile(idAmbiente, idResponsabile);
		return "responsabile/indexResponsabile.html";
	}

	@PostMapping("/dipendente/{id}")
	public String modificaStipendio(@ModelAttribute("stipendio") float stipendio, @PathVariable("id") Long id,
			Model model) {
		Personale personale = this.personaleService.getDipendente(id);
		this.personaleService.modificaStipendio(personale, stipendio);
		model.addAttribute("dipendente", personale);
		return "responsabile/dipendenteResponsabile.html";
	}

	@GetMapping("/responsabile/eliminaResp/{id}")
	public String eliminaResponsabile(@PathVariable("id") Long id,Model model){
		Personale dipendente = this.personaleService.getDipendente(id);
		this.personaleService.rimuoviResp(dipendente);
		model.addAttribute("dipendente", dipendente);
		return "responsabile/dipendenteResponsabile.html";
	}
}