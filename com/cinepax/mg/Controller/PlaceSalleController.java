package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.PlaceSalleRepository;
import com.cinepax.mg.entity.PlaceSalle;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "placeSalle")
public class PlaceSalleController {

	@Autowired
	private PlaceSalleRepository repository;


	@PostMapping()
	public ResponseEntity<PlaceSalle> save(@RequestBody PlaceSalle placeSalle){
	 	return ResponseEntity.ok(repository.save(placeSalle));
	}
	@PutMapping()
	public ResponseEntity<PlaceSalle> update(@RequestBody PlaceSalle placeSalle){
	 	return ResponseEntity.ok(repository.save(placeSalle));
	}
	@DeleteMapping()
	public ResponseEntity<PlaceSalle> delete(@RequestBody PlaceSalle placeSalle){
	 	repository.delete(placeSalle); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<PlaceSalle>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<PlaceSalle>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
