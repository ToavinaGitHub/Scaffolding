package com.kombarika.mg.Repository;


import com.kombarika.mg.Model.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> , JpaRepository<Etudiant, Integer> {









}
