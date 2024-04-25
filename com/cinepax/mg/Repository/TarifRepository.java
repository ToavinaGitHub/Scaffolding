package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.Tarif;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TarifRepository extends CrudRepository<Tarif, String> , JpaRepository<Tarif, String> {









}
