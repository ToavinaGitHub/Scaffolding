package com.scaffoldtesting.scaffoldtesting.repository;


import com.scaffoldtesting.scaffoldtesting.entity.Pays;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PaysRepository extends CrudRepository<Pays, Integer> , JpaRepository<Pays, Integer> {









}
