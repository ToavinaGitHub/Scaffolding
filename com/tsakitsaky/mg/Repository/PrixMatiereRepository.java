package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.PrixMatiere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PrixMatiereRepository extends CrudRepository<PrixMatiere, Integer> , JpaRepository<PrixMatiere, Integer> {









}
