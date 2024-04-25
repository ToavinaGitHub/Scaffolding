package com.cinepax.mg.Model;


import jakarta.persistence.*;



@Entity
@Table(name = "use_you")
public class UseYou {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@Column(name = "nom_a")
	String nomA;




	public UseYou(){}

	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getNomA(){
		return this.nomA;
	}
	public void setNomA(String nomA){
		this.nomA = nomA;
	}


}
