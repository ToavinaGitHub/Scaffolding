package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "payement")
public class Payement {

	@Id
	@Column(name = "id_payement")
	String idPayement;
	@JoinColumn(name = "id_vente_billet")
	@ManyToOne
	VenteBillet venteBillet;
	@Column(name = "montant")
	Double montant;
	@Column(name = "daty")
	TimeStamp daty;




	public Payement(){}

	public String getIdPayement(){
		return this.idPayement;
	}
	public void setIdPayement(String idPayement){
		this.idPayement = idPayement;
	}
	public VenteBillet getVenteBillet(){
		return this.venteBillet;
	}
	public void setVenteBillet(VenteBillet venteBillet){
		this.venteBillet = venteBillet;
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


}
