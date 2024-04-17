package com.test.mg.Repository;


import com.test.mg.Model.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DistrictRepository extends CrudRepository<District, Integer> , JpaRepository<District, Integer> {









}
