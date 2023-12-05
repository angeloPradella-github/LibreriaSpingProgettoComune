package com.corso.springdata.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corso.springdata.entity.Utente;
import com.corso.springdata.service.UtenteService;

@Controller
public class UtenteController {

	private UtenteService utenteservice;

	public Utente utenteLoggato = null;

	public UtenteController(UtenteService utenteservice) {
		super();
		this.utenteservice = utenteservice;
	}

	@GetMapping("/utenti")
	public String listaUtenti(Model model) {
		model.addAttribute("utenti", utenteservice.getTuttiUtenti());
		return "utenti";
	}

	@GetMapping("/utenti/nuovo")
	public String CreaUtente(Model model) {
		// Creiamo un nuovo utente
		Utente utente = new Utente();
		model.addAttribute("utente", utente);
		return "crea_utente";
	}

	@PostMapping("/utenti")
	public String CreaUtente(@ModelAttribute("utente") Utente utente, @RequestParam(name = "isAdmin") String isAdmin) {
		utente.setAdmin((isAdmin.equalsIgnoreCase("true")) ? true : false);
		utenteservice.salvaUtente(utente);
		return "redirect:/utenti";

	}

	//////////////////////////////////////////////////////////

//	@GetMapping("/login")
//	public String loginUtente(Model model) {
//		Utente utente = new Utente();
//		model.addAttribute("utente",utente);
//		return "login";
//	}
//	
//	
//	 
//	
//	@PostMapping("/login")
//	public String loginutente(@ModelAttribute("utente")Utente utente, Model model) {
//		
//		Optional<Utente> utentelog = utenteservice.loginUtente(utente.getUsername(), utente.getPassword());
//		
//		
//		
//		if(!utentelog.isEmpty()) {			
//			if(utentelog.get().isAdmin()) {
//				return "redirect:/utenti";
//			}else {
//				return "redirect:/libri";
//			}
//		}else {
//			return "redirect:/login";
//		}
//	}

	@GetMapping("/login")
	public String loginUtente(Model model) {

		Utente utente = new Utente();
		model.addAttribute("utente", utente);
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}

	@PostMapping("/login")
	public String loginutente(@ModelAttribute("utente") Utente utente) {

		Optional<Utente> utentelog = utenteservice.loginUtente(utente.getUsername(), utente.getPassword());

		if (!utentelog.isEmpty()) {
			this.utenteLoggato = utentelog.get();
			if (utentelog.get().isAdmin()) {
				return "redirect:/utenti";
			} else {
				return "redirect:/libri";
			}
		}
		return "redirect:/login";
	}

	@GetMapping("/cancellaUtente/{id}")
	public String cancellaUtente(@PathVariable Integer id) {
		utenteservice.cancellaUtenteById(id);
		return "redirect:/utenti";

	}

}
