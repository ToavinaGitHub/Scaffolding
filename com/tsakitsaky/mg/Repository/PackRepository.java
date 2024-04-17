package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Pack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PackRepository extends CrudRepository<Pack, String> , JpaRepository<Pack, String> {









}
