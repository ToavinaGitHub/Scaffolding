package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface GenreRepository extends CrudRepository<Genre, Integer> , JpaRepository<Genre, Integer> {









}
