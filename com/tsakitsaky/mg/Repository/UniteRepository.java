package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Unite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UniteRepository extends CrudRepository<Unite, String> , JpaRepository<Unite, String> {









}
