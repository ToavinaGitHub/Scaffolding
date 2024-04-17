package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "profil")
public class Profil {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_profil")
	Integer idProfil;
	@Column(name = "description")
	String description;




	public Profil(){}

	public Integer getIdProfil(){
		return this.idProfil;
	}
	public void setIdProfil(Integer idProfil){
		this.idProfil = idProfil;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}


}
