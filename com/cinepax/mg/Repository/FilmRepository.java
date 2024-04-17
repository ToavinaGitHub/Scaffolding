package com.cinepax.mg.Repository;


import com.cinepax.mg.Model.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FilmRepository extends CrudRepository<Film, String> , JpaRepository<Film, String> {









}
