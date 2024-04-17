package com.cinepax.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "prix_produit")
public class PrixProduit {

	@Column(name = "prix")
	Double prix;
	@Column(name = "daty")
	TimeStamp daty;
	@Column(name = "etat")
	Integer etat;
	@JoinColumn(name = "id_produit")
	@ManyToOne
	Produit produit;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_prix_produit")
	Integer idPrixProduit;




	public PrixProduit(){}

	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public Timestamp getDaty(){
		return this.daty;
	}
	public void setDaty(Timestamp daty){
		this.daty = daty;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public Produit getProduit(){
		return this.produit;
	}
	public void setProduit(Produit produit){
		this.produit = produit;
	}
	public Integer getIdPrixProduit(){
		return this.idPrixProduit;
	}
	public void setIdPrixProduit(Integer idPrixProduit){
		this.idPrixProduit = idPrixProduit;
	}


}
