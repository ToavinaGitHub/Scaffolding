package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "use_me")
public class UseMe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "nom_b")
	String nomB;
	@JoinColumn(name = "id_use_you")
	@ManyToOne
	UseYou useYou;




	public UseMe(){}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getNomB(){
		return this.nomB;
	}
	public void setNomB(String nomB){
		this.nomB = nomB;
	}
	public UseYou getUseYou(){
		return this.useYou;
	}
	public void setUseYou(UseYou useYou){
		this.useYou = useYou;
	}


}
