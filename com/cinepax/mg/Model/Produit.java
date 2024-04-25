package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "produit")
public class Produit {

	@Column(name = "prix")
	Double prix;
	@Column(name = "libelle")
	String libelle;
	@Id
	@Column(name = "id_produit")
	String idProduit;
	@Column(name = "etat")
	Integer etat;




	public Produit(){}

	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public String getIdProduit(){
		return this.idProduit;
	}
	public void setIdProduit(String idProduit){
		this.idProduit = idProduit;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}


}
