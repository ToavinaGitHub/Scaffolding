package com.tsakitsaky.mg.Repository;


import com.tsakitsaky.mg.Model.Profil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProfilRepository extends CrudRepository<Profil, Integer> , JpaRepository<Profil, Integer> {









}
