package com.scaffoldtesting.scaffoldtesting.controller;


import com.scaffoldtesting.scaffoldtesting.repository.PaysRepository;
import com.scaffoldtesting.scaffoldtesting.entity.Pays;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "pays")
public class PaysController {

	@Autowired
	private PaysRepository repository;


	@PostMapping()
	public ResponseEntity<Pays> save(@RequestBody Pays pays){
	 	return ResponseEntity.ok(repository.save(pays));
	}
	@PutMapping()
	public ResponseEntity<Pays> update(@RequestBody Pays pays){
	 	return ResponseEntity.ok(repository.save(pays));
	}
	@DeleteMapping()
	public ResponseEntity<Pays> delete(@RequestBody Pays pays){
	 	repository.delete(pays); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Pays>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Pays>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}
}
