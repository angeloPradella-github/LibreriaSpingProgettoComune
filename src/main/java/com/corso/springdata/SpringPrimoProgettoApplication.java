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
			
			
			
//			CREA N LIBRI
			
//			for(int i=0;i<20;i++) {
//				
//				Libro newlibro = new Libro();
//				newlibro.setAutore("autore"+i);
//				newlibro.setTitolo("nome"+i);
//				newlibro.setCasa_editrice("casaeditrice"+i);
//				newlibro.setPagine(200);
//				newlibro.setPrenotato(false);
//				intlibro.save(newlibro);
//			}
			
//			CREA N UTENTI
			
//			for(int i=0;i<10;i++) {
//				
//				Utente newutente = new Utente();
//				newutente.setUsername("utente"+i);
//				newutente.setPassword("pwd"+i);
//				newutente.setAdmin(false);
//				intutente.save(newutente);
//			}
//			
			
			
			
			Scanner scanStr = new Scanner(System.in);
			
			System.out.println("Inserisci username");
			String usernameInserito = scanStr.nextLine();
			
			System.out.println("Inserisci password");
			String passwordInserito = scanStr.nextLine();
			
			Optional<Utente> singoloUtente = intutente.findByUsernameAndPassword(usernameInserito, passwordInserito);
			if(singoloUtente.isEmpty()) {
				System.out.println("Credenziali errate");
			}else {
				Utente utentelog = singoloUtente.get();
				if(utentelog.isAdmin()==false) {
					
					Scanner scanInt = new Scanner(System.in);
					int azione = 0;
					
					while(azione!=3) {
						System.out.println("1)Consultazione libri\n2)Prenota libro\n3)Esci");
						azione = scanInt.nextInt();
						switch(azione) {
						case 1:
							
							Iterable<Libro> tuttilibri = intlibro.findAll();
							
							for(Libro str:tuttilibri) {
								System.out.println(str.toString());
							}
							
							break;
						case 2:
							
							System.out.println("Inserire il titolo del libro che si vuole prenotare");
							String titoloInserito=scanStr.next();
							System.out.println("Inserire l'autore del libro che si vuole prenotare");
							String autoreInserito=scanStr.next();
							
							Optional<Libro> libroUPDATE = intlibro.findByTitoloAndAutore(titoloInserito, autoreInserito);
							System.out.println(libroUPDATE.toString());
							
							if(libroUPDATE.isEmpty() == false) {
								
								Libro libro = libroUPDATE.get();
								if(libro.isPrenotato()) {
									System.out.println("Il libro è già stato prenotato");
								}else {
									libro.setPrenotato(true);
								}
								
								intlibro.save(libro);
								
								System.out.println(libro.toString());
								
							}else {
								
								System.out.println("Nessun libro trovato");
								
							}
							
							break;
						case 3:					
							System.out.println("Sei uscito");
							break;
						default:
							System.out.println("Azione non valida");
						}
					}
					
					scanInt.close();
					
				}else {
					
					int azione = 0;
					
					Scanner scanInt = new Scanner(System.in);
					
					while(azione!=5) {
						System.out.println("1)Inserisci utenti\n2)Inserisici libri\n3)Elimina libri\n4)Restituisci libro\n5)Esci");
						azione = scanInt.nextInt();
						switch(azione) {
						case 1:
							
							System.out.println("Inserisci username");
							String nuovousernameInserito = scanStr.next();
							
							System.out.println("Inserisci password");
							String nuovopasswordInserito = scanStr.next();
							
							System.out.println("L'utente è admin? 1)si    2)no");
							int admincheck = scanStr.nextInt();
							
							Utente newutente = new Utente();
							newutente.setUsername(nuovousernameInserito);
							newutente.setPassword(nuovopasswordInserito);
							if(admincheck==1) {
								newutente.setAdmin(true);
							}else {
								newutente.setAdmin(false);
							}
							
							intutente.save(newutente);
							
							break;
						case 2:
							
							System.out.println("Inserisci titolo");
							String nuovotitolo = scanStr.next();
							System.out.println("Inserisci autore");
							String nuovoautore = scanStr.next();
							System.out.println("Inserisci casa editrice");
							String nuovocasaeditrice = scanStr.next();
							System.out.println("Inserisci pagine");
							int nuovopagine = scanInt.nextInt();
							
							Libro newlibro = new Libro();
							newlibro.setAutore(nuovoautore);
							newlibro.setTitolo(nuovotitolo);
							newlibro.setCasa_editrice(nuovocasaeditrice);
							newlibro.setPagine(nuovopagine);
							newlibro.setPrenotato(false);
							intlibro.save(newlibro);
							
							break;
						case 3:
							
							System.out.println("Selezionare il titolo del libro da eliminare");
							String titolodelete = scanStr.next();
							
							System.out.println("Selezionare il nome dell'autore del libro da eliminare");
							String autoredelete = scanStr.next();
							
							Optional<Libro> libroDELETE = intlibro.findByTitoloAndAutore(titolodelete,autoredelete);
							if(titolodelete.isEmpty() || (autoredelete.isEmpty())) {
								
								System.out.println("Nessun elemento trovato");
								
							}else {
								
								intlibro.delete(libroDELETE.get());
								
							}
							
							break;
						case 4:

							System.out.println("Inserire il titolo del libro che si vuole restituire");
							String titoloInseritoAdmin=scanStr.next();
							System.out.println("Inserire l'autore del libro che si vuole restituire");
							String autoreInseritoAdmin=scanStr.next();
							
							Optional<Libro> libroUPDATEAD = intlibro.findByTitoloAndAutore(titoloInseritoAdmin, autoreInseritoAdmin);
							System.out.println(libroUPDATEAD.toString());
							
							if(libroUPDATEAD.isEmpty() == false) {
								
								Libro libro = libroUPDATEAD.get();
								if(libro.isPrenotato()) {
									libro.setPrenotato(false);	
								}else {
									System.out.println("Il libro è disponibile");
								}
								
								intlibro.save(libro);
								
								System.out.println(libro.toString());
								
							}else {
								
								System.out.println("Nessun libro trovato");
								
							}
							
							break;
							
						case 5:
							
							System.out.println("Sei uscito");
							
							break;
							
						default:
							System.out.println("Azione non valida");
						}
					}
					
				scanInt.close();	
				}
			}
			scanStr.close();
		};
	}
}
