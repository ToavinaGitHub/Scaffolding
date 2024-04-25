package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.DetailPack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DetailPackRepository extends CrudRepository<DetailPack, String> , JpaRepository<DetailPack, String> {









}
