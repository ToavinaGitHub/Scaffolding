package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.EtatBillet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EtatBilletRepository extends CrudRepository<EtatBillet, Integer> , JpaRepository<EtatBillet, Integer> {









}
