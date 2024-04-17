package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.UseYouRepository;
import com.cinepax.mg.entity.UseYou;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "useYou")
public class UseYouController {

	@Autowired
	private UseYouRepository repository;


	@PostMapping()
	public ResponseEntity<UseYou> save(@RequestBody UseYou useYou){
	 	return ResponseEntity.ok(repository.save(useYou));
	}
	@PutMapping()
	public ResponseEntity<UseYou> update(@RequestBody UseYou useYou){
	 	return ResponseEntity.ok(repository.save(useYou));
	}
	@DeleteMapping()
	public ResponseEntity<UseYou> delete(@RequestBody UseYou useYou){
	 	repository.delete(useYou); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<UseYou>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<UseYou>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
