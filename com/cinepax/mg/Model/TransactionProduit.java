package com.cinepax.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "transaction_produit")
public class TransactionProduit {

	@Column(name = "pu")
	Double pu;
	@Id
	@Column(name = "id_transaction_produit")
	String idTransactionProduit;
	@Column(name = "montant")
	Double montant;
	@Column(name = "daty")
	TimeStamp daty;
	@Column(name = "type")
	Integer type;
	@Column(name = "etat")
	Integer etat;
	@JoinColumn(name = "id_produit")
	@ManyToOne
	Produit produit;
	@Column(name = "quantite")
	Double quantite;




	public TransactionProduit(){}

	public Double getPu(){
		return this.pu;
	}
	public void setPu(Double pu){
		this.pu = pu;
	}
	public String getIdTransactionProduit(){
		return this.idTransactionProduit;
	}
	public void setIdTransactionProduit(String idTransactionProduit){
		this.idTransactionProduit = idTransactionProduit;
	}
	public Double getMontant(){
		return this.montant;
	}
	public void setMontant(Double montant){
		this.montant = montant;
	}
	public Timestamp getDaty(){
		return this.daty;
	}
	public void setDaty(Timestamp daty){
		this.daty = daty;
	}
	public Integer getType(){
		return this.type;
	}
	public void setType(Integer type){
		this.type = type;
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
	public Double getQuantite(){
		return this.quantite;
	}
	public void setQuantite(Double quantite){
		this.quantite = quantite;
	}


}
