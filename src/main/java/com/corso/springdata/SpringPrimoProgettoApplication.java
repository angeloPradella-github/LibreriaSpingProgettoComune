package com.corso.springdata;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.corso.springdata.entity.Libro;
import com.corso.springdata.entity.Utente;
import com.corso.springdata.repository.LibroRepository;
import com.corso.springdata.repository.UtenteRepository;

@SpringBootApplication
public class SpringPrimoProgettoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPrimoProgettoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(LibroRepository intlibro, UtenteRepository intutente) {
		
		return args ->{
			
			
		};
	}
}
