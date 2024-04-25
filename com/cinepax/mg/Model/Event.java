package com.cinepax.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "event")
public class Event {

	@Column(name = "date")
	TimeStamp date;
	@Column(name = "heure")
	TimeStamp heure;
	@Column(name = "prix")
	Double prix;
	@JoinColumn(name = "id_film")
	@ManyToOne
	Film film;
	@JoinColumn(name = "id_salle")
	@ManyToOne
	Salle salle;
	@Id
	@Column(name = "id_event")
	String idEvent;
	@Column(name = "etat")
	Integer etat;




	public Event(){}

	public Timestamp getDate(){
		return this.date;
	}
	public void setDate(Timestamp date){
		this.date = date;
	}
	public Timestamp getHeure(){
		return this.heure;
	}
	public void setHeure(Timestamp heure){
		this.heure = heure;
	}
	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public Film getFilm(){
		return this.film;
	}
	public void setFilm(Film film){
		this.film = film;
	}
	public Salle getSalle(){
		return this.salle;
	}
	public void setSalle(Salle salle){
		this.salle = salle;
	}
	public String getIdEvent(){
		return this.idEvent;
	}
	public void setIdEvent(String idEvent){
		this.idEvent = idEvent;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}


}
