package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.DataCsv;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DataCsvRepository extends CrudRepository<DataCsv, Integer> , JpaRepository<DataCsv, Integer> {









}
