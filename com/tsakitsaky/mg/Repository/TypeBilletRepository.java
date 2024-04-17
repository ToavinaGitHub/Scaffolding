package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.TypeBillet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TypeBilletRepository extends CrudRepository<TypeBillet, String> , JpaRepository<TypeBillet, String> {









}
