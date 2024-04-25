package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "matiere")
public class Matiere {

	@Id
	@Column(name = "id_matiere")
	String idMatiere;
	@Column(name = "prix")
	Double prix;
	@JoinColumn(name = "id_unite")
	@ManyToOne
	Unite unite;
	@Column(name = "libelle")
	String libelle;
	@Column(name = "qt_prix")
	Double qtPrix;




	public Matiere(){}

	public String getIdMatiere(){
		return this.idMatiere;
	}
	public void setIdMatiere(String idMatiere){
		this.idMatiere = idMatiere;
	}
	public Double getPrix(){
		return this.prix;
	}
	public void setPrix(Double prix){
		this.prix = prix;
	}
	public Unite getUnite(){
		return this.unite;
	}
	public void setUnite(Unite unite){
		this.unite = unite;
	}
	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public Double getQtPrix(){
		return this.qtPrix;
	}
	public void setQtPrix(Double qtPrix){
		this.qtPrix = qtPrix;
	}


}
