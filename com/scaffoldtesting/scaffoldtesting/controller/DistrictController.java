package com.scaffoldtesting.scaffoldtesting.controller;


import com.scaffoldtesting.scaffoldtesting.repository.DistrictRepository;
import com.scaffoldtesting.scaffoldtesting.entity.District;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "district")
public class DistrictController {

	@Autowired
	private DistrictRepository repository;


	@PostMapping()
	public ResponseEntity<District> save(@RequestBody District district){
	 	return ResponseEntity.ok(repository.save(district));
	}
	@PutMapping()
	public ResponseEntity<District> update(@RequestBody District district){
	 	return ResponseEntity.ok(repository.save(district));
	}
	@DeleteMapping()
	public ResponseEntity<District> delete(@RequestBody District district){
	 	repository.delete(district); return ResponseEntity.ok().build();
	}
	@GetMapping()
	public ResponseEntity<Iterable<District>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
