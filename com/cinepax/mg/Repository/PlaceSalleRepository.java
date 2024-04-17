package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.PlaceSalle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PlaceSalleRepository extends CrudRepository<PlaceSalle, Integer> , JpaRepository<PlaceSalle, Integer> {









}
