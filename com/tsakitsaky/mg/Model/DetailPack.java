package com.tsakitsaky.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "detail_pack")
public class DetailPack {

	@JoinColumn(name = "id_matiere")
	@ManyToOne
	Matiere matiere;
	@Id
	@Column(name = "id_detail_pack")
	String idDetailPack;
	@Column(name = "quantite")
	Double quantite;




	public DetailPack(){}

	public Matiere getMatiere(){
		return this.matiere;
	}
	public void setMatiere(Matiere matiere){
		this.matiere = matiere;
	}
	public String getIdDetailPack(){
		return this.idDetailPack;
	}
	public void setIdDetailPack(String idDetailPack){
		this.idDetailPack = idDetailPack;
	}
	public Double getQuantite(){
		return this.quantite;
	}
	public void setQuantite(Double quantite){
		this.quantite = quantite;
	}


}
