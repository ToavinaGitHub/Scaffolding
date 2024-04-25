package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UtilisateurRepository extends CrudRepository<Utilisateur, String> , JpaRepository<Utilisateur, String> {









}
