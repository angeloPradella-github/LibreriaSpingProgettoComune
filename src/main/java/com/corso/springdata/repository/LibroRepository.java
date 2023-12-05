package com.corso.springdata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.corso.springdata.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>  {

	public Optional<Libro> findByTitoloAndAutore(String titolo, String autore);
	
}
