package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.GenreRepository;
import com.tsakitsaky.mg.entity.Genre;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "genre")
public class GenreController {

	@Autowired
	private GenreRepository repository;


	@PostMapping()
	public ResponseEntity<Genre> save(@RequestBody Genre genre){
	 	return ResponseEntity.ok(repository.save(genre));
	}
	@PutMapping()
	public ResponseEntity<Genre> update(@RequestBody Genre genre){
	 	return ResponseEntity.ok(repository.save(genre));
	}
	@DeleteMapping()
	public ResponseEntity<Genre> delete(@RequestBody Genre genre){
	 	repository.delete(genre); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Genre>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Genre>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
