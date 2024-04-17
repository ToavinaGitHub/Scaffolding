package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "prix_matiere")
public class PrixMatiere {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_prix_matiere")
	Integer idPrixMatiere;
	@Column(name = "prix")
	Double prix;
	@JoinColumn(name = "id_unite")
	@ManyToOne
	Unite unite;
	@Column(name = "daty")
	TimeStamp daty;
	@Column(name = "qt_prix")
	Double qtPrix;




	public PrixMatiere(){}

	public Integer getIdPrixMatiere(){
		return this.idPrixMatiere;
	}
	public void setIdPrixMatiere(Integer idPrixMatiere){
		this.idPrixMatiere = idPrixMatiere;
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
	public Timestamp getDaty(){
		return this.daty;
	}
	public void setDaty(Timestamp daty){
		this.daty = daty;
	}
	public Double getQtPrix(){
		return this.qtPrix;
	}
	public void setQtPrix(Double qtPrix){
		this.qtPrix = qtPrix;
	}


}
