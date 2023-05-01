package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PersonaController{

	@GetMapping("/login")
public String login(Model model) {
  return "login.html";
}

	@GetMapping("/staff")
public String staff(Model model) {
  return "staff.html";
}
	@GetMapping("/dipendente")
public String dipendente(Model model) {
  return "dipendente.html";
}
}