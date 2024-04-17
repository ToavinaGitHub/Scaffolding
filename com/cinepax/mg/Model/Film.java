package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "film")
public class Film {

	@Column(name = "sary")
	String sary;
	@Id
	@Column(name = "id_film")
	String idFilm;
	@Column(name = "titre")
	String titre;
	@Column(name = "description")
	String description;
	@Column(name = "duree")
	Double duree;
	@JoinColumn(name = "id_genre_film")
	@ManyToOne
	GenreFilm genreFilm;
	@Column(name = "etat")
	Integer etat;




	public Film(){}

	public String getSary(){
		return this.sary;
	}
	public void setSary(String sary){
		this.sary = sary;
	}
	public String getIdFilm(){
		return this.idFilm;
	}
	public void setIdFilm(String idFilm){
		this.idFilm = idFilm;
	}
	public String getTitre(){
		return this.titre;
	}
	public void setTitre(String titre){
		this.titre = titre;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Double getDuree(){
		return this.duree;
	}
	public void setDuree(Double duree){
		this.duree = duree;
	}
	public GenreFilm getGenreFilm(){
		return this.genreFilm;
	}
	public void setGenreFilm(GenreFilm genreFilm){
		this.genreFilm = genreFilm;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}


}
