package com.cinepax.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "vente_billet")
public class VenteBillet {

	@Column(name = "places")
	String places;
	@Id
	@Column(name = "id_vente_billet")
	String idVenteBillet;
	@Column(name = "prix")
	Double prix;
	@Column(name = "date_vente")
	TimeStamp dateVente;
	@JoinColumn(name = "id_tarif")
	@ManyToOne
	Tarif tarif;
	@Column(name = "montant")
	Double montant;
	@JoinColumn(name = "id_event")
	@ManyToOne
	Event event;
	@Column(name = "etat")
	Integer etat;
	@Column(name = "nombre")
	Double nombre;




	public VenteBillet(){}

	public String getPlaces(){
		return this.places;
	}
	public void setPlaces(String places){
		this.places = places;
	}
	public String getIdVenteBillet(){
		return this.idVenteBillet;
	}
	public void setIdVenteBillet(String idVenteBillet){
		this.idVenteBillet = idVenteBillet;
	}
	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public Timestamp getDateVente(){
		return this.dateVente;
	}
	public void setDateVente(Timestamp dateVente){
		this.dateVente = dateVente;
	}
	public Tarif getTarif(){
		return this.tarif;
	}
	public void setTarif(Tarif tarif){
		this.tarif = tarif;
	}
	public Double getMontant(){
		return this.montant;
	}
	public void setMontant(Double montant){
		this.montant = montant;
	}
	public Event getEvent(){
		return this.event;
	}
	public void setEvent(Event event){
		this.event = event;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public Double getNombre(){
		return this.nombre;
	}
	public void setNombre(Double nombre){
		this.nombre = nombre;
	}


}
