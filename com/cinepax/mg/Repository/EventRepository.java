package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface EventRepository extends CrudRepository<Event, String> , JpaRepository<Event, String> {









}
