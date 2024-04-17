package com.kombarika.mg.Controller;


import com.kombarika.mg.Repository.EtudiantRepository;
import com.kombarika.mg.entity.Etudiant;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "etudiant")
public class EtudiantController {

	@Autowired
	private EtudiantRepository repository;


	@PostMapping()
	public ResponseEntity<Etudiant> save(@RequestBody Etudiant etudiant){
	 	return ResponseEntity.ok(repository.save(etudiant));
	}
	@PutMapping()
	public ResponseEntity<Etudiant> update(@RequestBody Etudiant etudiant){
	 	return ResponseEntity.ok(repository.save(etudiant));
	}
	@DeleteMapping()
	public ResponseEntity<Etudiant> delete(@RequestBody Etudiant etudiant){
	 	repository.delete(etudiant); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Etudiant>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Etudiant>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
