package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "place_salle")
public class PlaceSalle {

	@Column(name = "numero")
	String numero;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_place_salle")
	Integer idPlaceSalle;
	@Column(name = "range")
	String range;
	@JoinColumn(name = "id_salle")
	@ManyToOne
	Salle salle;
	@Column(name = "etat")
	Integer etat;




	public PlaceSalle(){}

	public String getNumero(){
		return this.numero;
	}
	public void setNumero(String numero){
		this.numero = numero;
	}
	public Integer getIdPlaceSalle(){
		return this.idPlaceSalle;
	}
	public void setIdPlaceSalle(Integer idPlaceSalle){
		this.idPlaceSalle = idPlaceSalle;
	}
	public String getRange(){
		return this.range;
	}
	public void setRange(String range){
		this.range = range;
	}
	public Salle getSalle(){
		return this.salle;
	}
	public void setSalle(Salle salle){
		this.salle = salle;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}


}
