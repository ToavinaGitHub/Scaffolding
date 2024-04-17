package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.UseYou;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UseYouRepository extends CrudRepository<UseYou, Integer> , JpaRepository<UseYou, Integer> {









}
