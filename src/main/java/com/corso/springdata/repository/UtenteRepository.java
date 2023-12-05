package com.corso.springdata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.corso.springdata.entity.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>  {

	public Optional<Utente> findByUsernameAndPassword(String username, String password);

}
