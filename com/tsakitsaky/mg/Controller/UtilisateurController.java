package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.UtilisateurRepository;
import com.tsakitsaky.mg.entity.Utilisateur;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "utilisateur")
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository repository;


	@PostMapping()
	public ResponseEntity<Utilisateur> save(@RequestBody Utilisateur utilisateur){
	 	return ResponseEntity.ok(repository.save(utilisateur));
	}
	@PutMapping()
	public ResponseEntity<Utilisateur> update(@RequestBody Utilisateur utilisateur){
	 	return ResponseEntity.ok(repository.save(utilisateur));
	}
	@DeleteMapping()
	public ResponseEntity<Utilisateur> delete(@RequestBody Utilisateur utilisateur){
	 	repository.delete(utilisateur); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Utilisateur>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Utilisateur>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
