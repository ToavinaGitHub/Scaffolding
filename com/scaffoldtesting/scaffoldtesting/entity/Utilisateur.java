package com.scaffoldtesting.scaffoldtesting.entity;


import jakarta.persistence.*;



@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Column(name = "password")
	String password;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_utilisateur")
	Integer idUtilisateur;
	@Column(name = "nom")
	String nom;
	@Column(name = "email")
	String email;




	public Utilisateur(){}

	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public Integer getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}


}
