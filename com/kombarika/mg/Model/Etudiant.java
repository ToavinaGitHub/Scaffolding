package com.kombarika.mg.Model;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "etudiant")
public class Etudiant {

	@Column(name = "sexe")
	Integer sexe;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "dtn")
	Date dtn;




	public Etudiant(){}

	public Integer getSexe(){
		return this.sexe;
	}
	public void setSexe(Integer sexe){
		this.sexe = sexe;
	}
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
	public String getPrenom(){
		return this.prenom;
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	public Date getDtn(){
		return this.dtn;
	}
	public void setDtn(Date dtn){
		this.dtn = dtn;
	}


}
