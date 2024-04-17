package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.FilmRepository;
import com.cinepax.mg.entity.Film;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "film")
public class FilmController {

	@Autowired
	private FilmRepository repository;


	@PostMapping()
	public ResponseEntity<Film> save(@RequestBody Film film){
	 	return ResponseEntity.ok(repository.save(film));
	}
	@PutMapping()
	public ResponseEntity<Film> update(@RequestBody Film film){
	 	return ResponseEntity.ok(repository.save(film));
	}
	@DeleteMapping()
	public ResponseEntity<Film> delete(@RequestBody Film film){
	 	repository.delete(film); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Film>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Film>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
