package com.corso.springdata.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.springdata.entity.Utente;
import com.corso.springdata.repository.UtenteRepository;
import com.corso.springdata.service.UtenteService;

import jakarta.servlet.http.HttpSession;

@Service
public class UtenteServiceImpl implements UtenteService {

	private UtenteRepository utenterepository;
	
	public UtenteServiceImpl(UtenteRepository utenterepository) {
		super();
		this.utenterepository = utenterepository;
	}

	@Override
	public Utente salvaUtente(Utente utente) {
		return utenterepository.save(utente);
	}

	@Override
	public List<Utente> getTuttiUtenti() {
		return utenterepository.findAll();
	}

	@Override
	public Optional<Utente> loginUtente(String username, String password) {
		
		Optional<Utente> utente = utenterepository.findByUsernameAndPassword(username, password);	
		return utente;
	}
	
	@Override
	public void cancellaUtenteById(Integer id) {
		utenterepository.deleteById(id);
		
	}

}
