package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.TransactionMatiere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TransactionMatiereRepository extends CrudRepository<TransactionMatiere, String> , JpaRepository<TransactionMatiere, String> {









}
