package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.LivraisonRepository;
import com.tsakitsaky.mg.entity.Livraison;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "livraison")
public class LivraisonController {

	@Autowired
	private LivraisonRepository repository;


	@PostMapping()
	public ResponseEntity<Livraison> save(@RequestBody Livraison livraison){
	 	return ResponseEntity.ok(repository.save(livraison));
	}
	@PutMapping()
	public ResponseEntity<Livraison> update(@RequestBody Livraison livraison){
	 	return ResponseEntity.ok(repository.save(livraison));
	}
	@DeleteMapping()
	public ResponseEntity<Livraison> delete(@RequestBody Livraison livraison){
	 	repository.delete(livraison); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Livraison>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Livraison>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
