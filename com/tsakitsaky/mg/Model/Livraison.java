package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "livraison")
public class Livraison {

	@Id
	@Column(name = "id_livraison")
	String idLivraison;
	@JoinColumn(name = "id_vente_billet")
	@ManyToOne
	VenteBillet venteBillet;
	@Column(name = "daty")
	TimeStamp daty;
	@Column(name = "etat")
	Integer etat;




	public Livraison(){}

	public String getIdLivraison(){
		return this.idLivraison;
	}
	public void setIdLivraison(String idLivraison){
		this.idLivraison = idLivraison;
	}
	public VenteBillet getVenteBillet(){
		return this.venteBillet;
	}
	public void setVenteBillet(VenteBillet venteBillet){
		this.venteBillet = venteBillet;
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


}
