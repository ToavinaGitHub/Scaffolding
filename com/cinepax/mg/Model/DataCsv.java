package com.cinepax.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.sql.Time;


@Entity
@Table(name = "data_csv")
public class DataCsv {

	@Column(name = "heure")
	Time heure;
	@Column(name = "categorie")
	String categorie;
	@Column(name = "num_seance")
	String numSeance;
	@Column(name = "salle")
	String salle;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "film")
	String film;
	@Column(name = "daty")
	TimeStamp daty;




	public DataCsv(){}

	public Time getHeure(){
		return this.heure;
	}
	public void setHeure(Time heure){
		this.heure = heure;
	}
	public String getCategorie(){
		return this.categorie;
	}
	public void setCategorie(String categorie){
		this.categorie = categorie;
	}
	public String getNumSeance(){
		return this.numSeance;
	}
	public void setNumSeance(String numSeance){
		this.numSeance = numSeance;
	}
	public String getSalle(){
		return this.salle;
	}
	public void setSalle(String salle){
		this.salle = salle;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getFilm(){
		return this.film;
	}
	public void setFilm(String film){
		this.film = film;
	}
	public Timestamp getDaty(){
		return this.daty;
	}
	public void setDaty(Timestamp daty){
		this.daty = daty;
	}


}
