package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "details_vente_billet")
public class DetailsVenteBillet {

	@JoinColumn(name = "id_vente_billet")
	@ManyToOne
	VenteBillet venteBillet;
	@Id
	@Column(name = "id_details_vente_billet")
	String idDetailsVenteBillet;
	@JoinColumn(name = "id_place_salle")
	@ManyToOne
	PlaceSalle placeSalle;




	public DetailsVenteBillet(){}

	public VenteBillet getVenteBillet(){
		return this.venteBillet;
	}
	public void setVenteBillet(VenteBillet venteBillet){
		this.venteBillet = venteBillet;
	}
	public String getIdDetailsVenteBillet(){
		return this.idDetailsVenteBillet;
	}
	public void setIdDetailsVenteBillet(String idDetailsVenteBillet){
		this.idDetailsVenteBillet = idDetailsVenteBillet;
	}
	public PlaceSalle getPlaceSalle(){
		return this.placeSalle;
	}
	public void setPlaceSalle(PlaceSalle placeSalle){
		this.placeSalle = placeSalle;
	}


}
