package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "transaction_matiere")
public class TransactionMatiere {

	@Column(name = "date")
	TimeStamp date;
	@Id
	@Column(name = "id_transaction_matiere")
	String idTransactionMatiere;
	@JoinColumn(name = "id_unite")
	@ManyToOne
	Unite unite;
	@Column(name = "montant")
	Double montant;
	@Column(name = "etat")
	Integer etat;
	@Column(name = "quantite")
	Double quantite;




	public TransactionMatiere(){}

	public Timestamp getDate(){
		return this.date;
	}
	public void setDate(Timestamp date){
		this.date = date;
	}
	public String getIdTransactionMatiere(){
		return this.idTransactionMatiere;
	}
	public void setIdTransactionMatiere(String idTransactionMatiere){
		this.idTransactionMatiere = idTransactionMatiere;
	}
	public Unite getUnite(){
		return this.unite;
	}
	public void setUnite(Unite unite){
		this.unite = unite;
	}
	public Double getMontant(){
		return this.montant;
	}
	public void setMontant(Double montant){
		this.montant = montant;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public Double getQuantite(){
		return this.quantite;
	}
	public void setQuantite(Double quantite){
		this.quantite = quantite;
	}


}
