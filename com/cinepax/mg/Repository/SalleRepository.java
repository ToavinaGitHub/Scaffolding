package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.Salle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SalleRepository extends CrudRepository<Salle, String> , JpaRepository<Salle, String> {









}
