package com.scaffoldtesting.scaffoldtesting.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "district")
public class District {

	@Column(name = "nom_district")
	String nomDistrict;
	@JoinColumn(name = "id_region")
	@ManyToOne
	Region region;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_district")
	Integer idDistrict;

	public District(){}

	public String getNomDistrict(){
		return this.nomDistrict;
	}
	public void setNomDistrict(String nomDistrict){
		this.nomDistrict = nomDistrict;
	}
	public Region getRegion(){
		return this.region;
	}
	public void setRegion(Region region){
		this.region = region;
	}
	public Integer getIdDistrict(){
		return this.idDistrict;
	}
	public void setIdDistrict(Integer idDistrict){
		this.idDistrict = idDistrict;
	}


}
