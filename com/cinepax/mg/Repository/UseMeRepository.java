package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.UseMe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UseMeRepository extends CrudRepository<UseMe, Integer> , JpaRepository<UseMe, Integer> {









}
