package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.MatiereRepository;
import com.tsakitsaky.mg.entity.Matiere;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "matiere")
public class MatiereController {

	@Autowired
	private MatiereRepository repository;


	@PostMapping()
	public ResponseEntity<Matiere> save(@RequestBody Matiere matiere){
	 	return ResponseEntity.ok(repository.save(matiere));
	}
	@PutMapping()
	public ResponseEntity<Matiere> update(@RequestBody Matiere matiere){
	 	return ResponseEntity.ok(repository.save(matiere));
	}
	@DeleteMapping()
	public ResponseEntity<Matiere> delete(@RequestBody Matiere matiere){
	 	repository.delete(matiere); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Matiere>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Matiere>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
