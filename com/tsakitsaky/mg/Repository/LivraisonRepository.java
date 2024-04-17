package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Livraison;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LivraisonRepository extends CrudRepository<Livraison, String> , JpaRepository<Livraison, String> {









}
