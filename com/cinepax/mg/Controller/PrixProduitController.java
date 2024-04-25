package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.PrixProduitRepository;
import com.cinepax.mg.entity.PrixProduit;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "prixProduit")
public class PrixProduitController {

	@Autowired
	private PrixProduitRepository repository;


	@PostMapping()
	public ResponseEntity<PrixProduit> save(@RequestBody PrixProduit prixProduit){
	 	return ResponseEntity.ok(repository.save(prixProduit));
	}
	@PutMapping()
	public ResponseEntity<PrixProduit> update(@RequestBody PrixProduit prixProduit){
	 	return ResponseEntity.ok(repository.save(prixProduit));
	}
	@DeleteMapping()
	public ResponseEntity<PrixProduit> delete(@RequestBody PrixProduit prixProduit){
	 	repository.delete(prixProduit); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<PrixProduit>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<PrixProduit>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
