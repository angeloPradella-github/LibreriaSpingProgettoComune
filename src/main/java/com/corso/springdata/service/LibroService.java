package com.corso.springdata.service;

import java.util.List;
import java.util.Optional;

import com.corso.springdata.entity.Libro;


public interface LibroService {

	List<Libro> getTuttiLibri();
	
	void cancellaLibroById(Integer id);
	
	Libro salvaLibro(Libro libro);
	
	void prenotaLibro(Libro libro);
	
	Optional <Libro>  findById(Integer id);
	
	void restituisciLibro(Libro libro);
	
	Optional<Libro> getLibroById(Integer id);
	
}
