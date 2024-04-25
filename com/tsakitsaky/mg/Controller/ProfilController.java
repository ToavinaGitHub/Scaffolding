package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.ProfilRepository;
import com.tsakitsaky.mg.entity.Profil;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "profil")
public class ProfilController {

	@Autowired
	private ProfilRepository repository;


	@PostMapping()
	public ResponseEntity<Profil> save(@RequestBody Profil profil){
	 	return ResponseEntity.ok(repository.save(profil));
	}
	@PutMapping()
	public ResponseEntity<Profil> update(@RequestBody Profil profil){
	 	return ResponseEntity.ok(repository.save(profil));
	}
	@DeleteMapping()
	public ResponseEntity<Profil> delete(@RequestBody Profil profil){
	 	repository.delete(profil); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Profil>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Profil>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
