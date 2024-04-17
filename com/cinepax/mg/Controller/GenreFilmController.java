package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.GenreFilmRepository;
import com.cinepax.mg.entity.GenreFilm;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "genreFilm")
public class GenreFilmController {

	@Autowired
	private GenreFilmRepository repository;


	@PostMapping()
	public ResponseEntity<GenreFilm> save(@RequestBody GenreFilm genreFilm){
	 	return ResponseEntity.ok(repository.save(genreFilm));
	}
	@PutMapping()
	public ResponseEntity<GenreFilm> update(@RequestBody GenreFilm genreFilm){
	 	return ResponseEntity.ok(repository.save(genreFilm));
	}
	@DeleteMapping()
	public ResponseEntity<GenreFilm> delete(@RequestBody GenreFilm genreFilm){
	 	repository.delete(genreFilm); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<GenreFilm>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<GenreFilm>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
