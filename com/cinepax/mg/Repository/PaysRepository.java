package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.Pays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PaysRepository extends CrudRepository<Pays, Integer> , JpaRepository<Pays, Integer> {









}
