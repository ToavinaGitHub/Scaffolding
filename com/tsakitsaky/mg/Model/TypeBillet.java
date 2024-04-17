package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "type_billet")
public class TypeBillet {

	@Id
	@Column(name = "id_type_billet")
	String idTypeBillet;
	@Column(name = "prix")
	Double prix;
	@Column(name = "description")
	String description;




	public TypeBillet(){}

	public String getIdTypeBillet(){
		return this.idTypeBillet;
	}
	public void setIdTypeBillet(String idTypeBillet){
		this.idTypeBillet = idTypeBillet;
	}
	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}


}
