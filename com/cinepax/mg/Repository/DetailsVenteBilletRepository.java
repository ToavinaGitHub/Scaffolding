package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.DetailsVenteBillet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DetailsVenteBilletRepository extends CrudRepository<DetailsVenteBillet, String> , JpaRepository<DetailsVenteBillet, String> {









}
