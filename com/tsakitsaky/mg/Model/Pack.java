package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "pack")
public class Pack {

	@JoinColumn(name = "id_type_billet")
	@ManyToOne
	TypeBillet typeBillet;
	@Column(name = "img")
	String img;
	@Column(name = "prix")
	Double prix;
	@Column(name = "nom")
	String nom;
	@Id
	@Column(name = "id_pack")
	String idPack;




	public Pack(){}

	public TypeBillet getTypeBillet(){
		return this.typeBillet;
	}
	public void setTypeBillet(TypeBillet typeBillet){
		this.typeBillet = typeBillet;
	}
	public String getImg(){
		return this.img;
	}
	public void setImg(String img){
		this.img = img;
	}
	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getIdPack(){
		return this.idPack;
	}
	public void setIdPack(String idPack){
		this.idPack = idPack;
	}


}
