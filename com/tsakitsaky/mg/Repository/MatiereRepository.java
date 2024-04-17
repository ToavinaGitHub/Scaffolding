package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Matiere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MatiereRepository extends CrudRepository<Matiere, String> , JpaRepository<Matiere, String> {









}
