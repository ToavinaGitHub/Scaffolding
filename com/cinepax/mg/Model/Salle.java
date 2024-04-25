package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "salle")
public class Salle {

	@Column(name = "capacite")
	Double capacite;
	@Id
	@Column(name = "id_salle")
	String idSalle;
	@Column(name = "etat")
	Integer etat;
	@Column(name = "nom")
	String nom;




	public Salle(){}

	public Double getCapacite(){
		return this.capacite;
	}
	public void setCapacite(Double capacite){
		this.capacite = capacite;
	}
	public String getIdSalle(){
		return this.idSalle;
	}
	public void setIdSalle(String idSalle){
		this.idSalle = idSalle;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}


}
