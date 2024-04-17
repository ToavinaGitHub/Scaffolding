package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.UniteRepository;
import com.tsakitsaky.mg.entity.Unite;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "unite")
public class UniteController {

	@Autowired
	private UniteRepository repository;


	@PostMapping()
	public ResponseEntity<Unite> save(@RequestBody Unite unite){
	 	return ResponseEntity.ok(repository.save(unite));
	}
	@PutMapping()
	public ResponseEntity<Unite> update(@RequestBody Unite unite){
	 	return ResponseEntity.ok(repository.save(unite));
	}
	@DeleteMapping()
	public ResponseEntity<Unite> delete(@RequestBody Unite unite){
	 	repository.delete(unite); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Unite>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Unite>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
