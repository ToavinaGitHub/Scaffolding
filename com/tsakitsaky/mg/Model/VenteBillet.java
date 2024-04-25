package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "vente_billet")
public class VenteBillet {

	@Id
	@Column(name = "id_vente_billet")
	String idVenteBillet;
	@Column(name = "client")
	String client;
	@Column(name = "lieu_livraison")
	String lieuLivraison;
	@Column(name = "contact_client")
	String contactClient;
	@Column(name = "daty")
	TimeStamp daty;
	@JoinColumn(name = "id_etat_billet")
	@ManyToOne
	EtatBillet etatBillet;
	@JoinColumn(name = "id_pack")
	@ManyToOne
	Pack pack;




	public VenteBillet(){}

	public String getIdVenteBillet(){
		return this.idVenteBillet;
	}
	public void setIdVenteBillet(String idVenteBillet){
		this.idVenteBillet = idVenteBillet;
	}
	public String getClient(){
		return this.client;
	}
	public void setClient(String client){
		this.client = client;
	}
	public String getLieuLivraison(){
		return this.lieuLivraison;
	}
	public void setLieuLivraison(String lieuLivraison){
		this.lieuLivraison = lieuLivraison;
	}
	public String getContactClient(){
		return this.contactClient;
	}
	public void setContactClient(String contactClient){
		this.contactClient = contactClient;
	}
	public Timestamp getDaty(){
		return this.daty;
	}
	public void setDaty(Timestamp daty){
		this.daty = daty;
	}
	public EtatBillet getEtatBillet(){
		return this.etatBillet;
	}
	public void setEtatBillet(EtatBillet etatBillet){
		this.etatBillet = etatBillet;
	}
	public Pack getPack(){
		return this.pack;
	}
	public void setPack(Pack pack){
		this.pack = pack;
	}


}
