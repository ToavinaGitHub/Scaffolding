package com.kombarika.mg.Repository;


import com.kombarika.mg.Model.Pays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PaysRepository extends CrudRepository<Pays, Integer> , JpaRepository<Pays, Integer> {









}
