package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Payement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PayementRepository extends CrudRepository<Payement, String> , JpaRepository<Payement, String> {









}
