package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.VenteBillet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface VenteBilletRepository extends CrudRepository<VenteBillet, String> , JpaRepository<VenteBillet, String> {









}
