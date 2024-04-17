package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "tarif")
public class Tarif {

	@Column(name = "heure_fin")
	Double heureFin;
	@Id
	@Column(name = "id_tarif")
	String idTarif;
	@Column(name = "description")
	String description;
	@Column(name = "montant")
	Double montant;
	@Column(name = "heure_debut")
	Double heureDebut;
	@Column(name = "age")
	String age;




	public Tarif(){}

	public Double getHeureFin(){
		return this.heureFin;
	}
	public void setHeureFin(Double heureFin){
		this.heureFin = heureFin;
	}
	public String getIdTarif(){
		return this.idTarif;
	}
	public void setIdTarif(String idTarif){
		this.idTarif = idTarif;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Double getMontant(){
		return this.montant;
	}
	public void setMontant(Double montant){
		this.montant = montant;
	}
	public Double getHeureDebut(){
		return this.heureDebut;
	}
	public void setHeureDebut(Double heureDebut){
		this.heureDebut = heureDebut;
	}
	public String getAge(){
		return this.age;
	}
	public void setAge(String age){
		this.age = age;
	}


}
