package com.corso.springdata.service;

import java.util.List;
import java.util.Optional;

//import java.util.List;
import com.corso.springdata.entity.Utente;

public interface UtenteService {

	List<Utente> getTuttiUtenti();
	
	void cancellaUtenteById(Integer id);
	
	Utente salvaUtente(Utente utente);
	
	Optional<Utente> loginUtente(String username,String password);
	
}
