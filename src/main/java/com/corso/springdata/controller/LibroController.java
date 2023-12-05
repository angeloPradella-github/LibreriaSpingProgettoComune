package com.corso.springdata.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.corso.springdata.entity.Libro;
import com.corso.springdata.entity.Utente;
import com.corso.springdata.service.LibroService;

@Controller
public class LibroController {

	private LibroService libroservice;

	private List<Libro> listaCarrello = new ArrayList<>();
	
	
	private HashMap<Integer, List<Libro>> carrello = new HashMap<>();

	@Autowired
	private UtenteController utenteController;

	public LibroController(LibroService libroservice) {
		super();
		this.libroservice = libroservice;
	}

	@GetMapping("/libri")
	public String listaLibri(Model model) {
		
		Integer userId = utenteController.utenteLoggato.getId();
		
		List<Libro> carrelloUtente = carrello.getOrDefault(userId, new ArrayList<>());

		model.addAttribute("libri", libroservice.getTuttiLibri());
		model.addAttribute("carrello", carrello);
		model.addAttribute("utenteLoggato", utenteController.utenteLoggato);
		model.addAttribute("dimensioneCarrelloUtente", carrelloUtente.size());
		return "libri";
	}
	
	@GetMapping("/libri/nuovo")
	public String CreaUtente(Model model) {
		//Creiamo un nuovo utente
		Libro libro = new Libro();
		model.addAttribute("libro",libro);
		return "crea_libro";
	}
	
	@PostMapping("/libri")
	public String CreaUtente(@ModelAttribute("libro")Libro libro) {
		
		libroservice.salvaLibro(libro);
		return"redirect:/utenti";
		
	}
	

	@GetMapping("/carrello")
	public String mostraCarrello(Model model) {
		Utente utenteLoggato = utenteController.utenteLoggato;
		
		
		
		List<Libro> carrelloUtente = carrello.getOrDefault(utenteLoggato, new ArrayList<>());

		model.addAttribute("libri", libroservice.getTuttiLibri());
		model.addAttribute("carrello", carrello);
		model.addAttribute("utenteLoggato", utenteLoggato);
		model.addAttribute("dimensioneCarrelloUtente", carrelloUtente.size());

		return "carrelloUtente";
	}

	@GetMapping("/cancellaLibro/{id}")
	public String CancellaLibro(@PathVariable Integer id) {
		libroservice.cancellaLibroById(id);
		return "redirect:/libriAdmin";

	}

	@GetMapping("/prenotaLibro/{id}")
	public String prenotaLibro(Model model, @PathVariable(name = "id") Integer id) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		Optional<Libro> librofind = libroservice.findById(id);
		librofind.get().setPrenotato(true);
		libroservice.prenotaLibro(librofind.get());
		return "redirect:/libri";
	}

	@GetMapping("/libriAdmin")
	public String libriAdmin(Model model) {
		model.addAttribute("libri", libroservice.getTuttiLibri());
		return "libriAdmin";
	}

	@GetMapping("/restituisciLibro/{id}")
	public String restituisciLibro(Model model, @PathVariable(name = "id") Integer id) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		Optional<Libro> librofind = libroservice.findById(id);
		librofind.get().setPrenotato(false);
		libroservice.restituisciLibro(librofind.get());
		return "redirect:/libriAdmin";
	}

	@GetMapping("/getLibriDaAcquistare")
	public String getLibriDaAcquistare(Model model) {
		model.addAttribute("libriDaAcquistare", libroservice.getTuttiLibri());
		return "libriDaAcquistare";
	}

	@GetMapping("/aggiungiCarrello/{id}")
	public String aggiungiCarrello(@PathVariable Integer id) {
		Optional<Libro> libroComprato = libroservice.getLibroById(id);

		Utente utenteLoggato = this.utenteController.utenteLoggato;
		System.out.println(utenteLoggato);
		if (utenteLoggato.getId() != null) {
//			listaCarrello.add(libroComprato.get());
			List<Libro> currentList = new ArrayList();
			if (carrello.get(utenteLoggato.getId()) != null) {
				currentList = carrello.get(utenteLoggato.getId());
			} 
				System.out.println(currentList);
				currentList.add(libroComprato.get());
				carrello.put(utenteLoggato.getId(), currentList);
			

			System.out.println("________________CARRELLOOOOOOO________________________");
			for (Entry<Integer, List<Libro>> entry : carrello.entrySet()) {
				Integer utente = entry.getKey();
				List<Libro> libri = entry.getValue();

//				System.out.println("Utente: " + utente.getUsername());
				System.out.println("Libri nel carrello:");

				for (Libro libro : libri) {
					System.out.println("  - " + libro.getTitolo());
				}

				System.out.println(); // Aggiungi una riga vuota per separare gli utenti
			}
			System.out.println("________________________________________");
			return "redirect:/libri";
		} else {
			return "redirect:/error";
		}

	}
	

	 
	
	

}
