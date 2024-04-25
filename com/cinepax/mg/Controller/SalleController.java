package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.SalleRepository;
import com.cinepax.mg.entity.Salle;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "salle")
public class SalleController {

	@Autowired
	private SalleRepository repository;


	@PostMapping()
	public ResponseEntity<Salle> save(@RequestBody Salle salle){
	 	return ResponseEntity.ok(repository.save(salle));
	}
	@PutMapping()
	public ResponseEntity<Salle> update(@RequestBody Salle salle){
	 	return ResponseEntity.ok(repository.save(salle));
	}
	@DeleteMapping()
	public ResponseEntity<Salle> delete(@RequestBody Salle salle){
	 	repository.delete(salle); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Salle>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Salle>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
