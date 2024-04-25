package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "genre_film")
public class GenreFilm {

	@Column(name = "libelle")
	String libelle;
	@Column(name = "etat")
	Integer etat;
	@Id
	@Column(name = "id_genre")
	String idGenre;




	public GenreFilm(){}

	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public String getIdGenre(){
		return this.idGenre;
	}
	public void setIdGenre(String idGenre){
		this.idGenre = idGenre;
	}


}
