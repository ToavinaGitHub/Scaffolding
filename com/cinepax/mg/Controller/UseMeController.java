package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.UseMeRepository;
import com.cinepax.mg.entity.UseMe;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "useMe")
public class UseMeController {

	@Autowired
	private UseMeRepository repository;


	@PostMapping()
	public ResponseEntity<UseMe> save(@RequestBody UseMe useMe){
	 	return ResponseEntity.ok(repository.save(useMe));
	}
	@PutMapping()
	public ResponseEntity<UseMe> update(@RequestBody UseMe useMe){
	 	return ResponseEntity.ok(repository.save(useMe));
	}
	@DeleteMapping()
	public ResponseEntity<UseMe> delete(@RequestBody UseMe useMe){
	 	repository.delete(useMe); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<UseMe>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<UseMe>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
