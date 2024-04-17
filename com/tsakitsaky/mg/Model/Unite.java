package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "unite")
public class Unite {

	@Id
	@Column(name = "id_unite")
	String idUnite;
	@Column(name = "libelle")
	String libelle;




	public Unite(){}

	public String getIdUnite(){
		return this.idUnite;
	}
	public void setIdUnite(String idUnite){
		this.idUnite = idUnite;
	}
	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}


}
