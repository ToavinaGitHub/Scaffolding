package com.scaffoldtesting.scaffoldtesting.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "pays")
public class Pays {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "nom")
	String nom;




	public Pays(){}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}


}
