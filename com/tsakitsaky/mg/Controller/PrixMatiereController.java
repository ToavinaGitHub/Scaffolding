package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.PrixMatiereRepository;
import com.tsakitsaky.mg.entity.PrixMatiere;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "prixMatiere")
public class PrixMatiereController {

	@Autowired
	private PrixMatiereRepository repository;


	@PostMapping()
	public ResponseEntity<PrixMatiere> save(@RequestBody PrixMatiere prixMatiere){
	 	return ResponseEntity.ok(repository.save(prixMatiere));
	}
	@PutMapping()
	public ResponseEntity<PrixMatiere> update(@RequestBody PrixMatiere prixMatiere){
	 	return ResponseEntity.ok(repository.save(prixMatiere));
	}
	@DeleteMapping()
	public ResponseEntity<PrixMatiere> delete(@RequestBody PrixMatiere prixMatiere){
	 	repository.delete(prixMatiere); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<PrixMatiere>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<PrixMatiere>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
