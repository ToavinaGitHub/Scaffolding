package com.test.mg.Controller;


import com.test.mg.Repository.DistrictRepository;
import com.test.mg.entity.District;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


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
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<District>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<District>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
