package com.test.mg.Repository;


import com.test.mg.Model.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> , JpaRepository<Utilisateur, Integer> {









}
