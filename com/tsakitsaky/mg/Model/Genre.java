package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "genre")
public class Genre {

	@Column(name = "libelle")
	String libelle;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_genre")
	Integer idGenre;




	public Genre(){}

	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public Integer getIdGenre(){
		return this.idGenre;
	}
	public void setIdGenre(Integer idGenre){
		this.idGenre = idGenre;
	}


}
