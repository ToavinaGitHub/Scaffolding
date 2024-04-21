package com.testa.mg.Repository;


import com.testa.mg.Model.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RegionRepository extends CrudRepository<Region, Integer> , JpaRepository<Region, Integer> {









}
