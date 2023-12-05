package com.corso.springdata.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.corso.springdata.entity.Libro;
import com.corso.springdata.repository.LibroRepository;
import com.corso.springdata.repository.UtenteRepository;
import com.corso.springdata.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService{

	private LibroRepository librorepository;
	
	public LibroServiceImpl(LibroRepository librorepository) {
		super();
		this.librorepository = librorepository;
	}

	@Override
	public List<Libro> getTuttiLibri() {
		return librorepository.findAll();
	}

	@Override
	public void cancellaLibroById(Integer id) {
		librorepository.deleteById(id);		
	}

	@Override
	public Libro salvaLibro(Libro libro) {
		return librorepository.save(libro);
	}

	@Override
	public void prenotaLibro(Libro libro) {
		libro.setPrenotato(true);
		librorepository.save(libro);
		
	}
	
	@Override
	public Optional<Libro> findById(Integer id) {
		return librorepository.findById(id);
	}

	@Override
	public void restituisciLibro(Libro libro) {
		libro.setPrenotato(false);
		librorepository.save(libro);		
	}
	
	@Override
	public Optional<Libro> getLibroById(Integer id) {
		return librorepository.findById(id);
	}

}
