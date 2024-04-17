package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "etat_billet")
public class EtatBillet {

	@Column(name = "description")
	String description;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_etat_billet")
	Integer idEtatBillet;




	public EtatBillet(){}

	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public Integer getIdEtatBillet(){
		return this.idEtatBillet;
	}
	public void setIdEtatBillet(Integer idEtatBillet){
		this.idEtatBillet = idEtatBillet;
	}


}
