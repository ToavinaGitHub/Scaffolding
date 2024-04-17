package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@JoinColumn(name = "id_profil")
	@ManyToOne
	Profil profil;
	@Id
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "contact")
	String contact;
	@Column(name = "adresse")
	String adresse;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "date_naissance")
	TimeStamp dateNaissance;
	@JoinColumn(name = "id_genre")
	@ManyToOne
	Genre genre;




	public Utilisateur(){}

	public Profil getProfil(){
		return this.profil;
	}
	public void setProfil(Profil profil){
		this.profil = profil;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public String getContact(){
		return this.contact;
	}
	public void setContact(String contact){
		this.contact = contact;
	}
	public String getAdresse(){
		return this.adresse;
	}
	public void setAdresse(String adresse){
		this.adresse = adresse;
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
	public Timestamp getDateNaissance(){
		return this.dateNaissance;
	}
	public void setDateNaissance(Timestamp dateNaissance){
		this.dateNaissance = dateNaissance;
	}
	public Genre getGenre(){
		return this.genre;
	}
	public void setGenre(Genre genre){
		this.genre = genre;
	}


}
