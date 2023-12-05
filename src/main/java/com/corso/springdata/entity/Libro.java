package com.corso.springdata.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Table;

@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String titolo;
	private String autore;
	private String casa_editrice;
	private int pagine;
	private boolean isPrenotato;
	
	public Libro() {}
	
	public Libro(String titolo, String autore, String casa_editrice, int pagine, boolean isPrenotato) {
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.casa_editrice = casa_editrice;
		this.pagine = pagine;
		this.isPrenotato = isPrenotato;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getCasa_editrice() {
		return casa_editrice;
	}
	public void setCasa_editrice(String casa_editrice) {
		this.casa_editrice = casa_editrice;
	}
	public int getPagine() {
		return pagine;
	}
	public void setPagine(int pagine) {
		this.pagine = pagine;
	}
	
	public boolean isPrenotato() {
		return isPrenotato;
	}
	public void setPrenotato(boolean isPrenotato) {
		this.isPrenotato = isPrenotato;
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", autore=" + autore + "]";
	}

	
}
