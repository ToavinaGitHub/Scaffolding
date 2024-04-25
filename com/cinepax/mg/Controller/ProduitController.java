package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.ProduitRepository;
import com.cinepax.mg.entity.Produit;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "produit")
public class ProduitController {

	@Autowired
	private ProduitRepository repository;


	@PostMapping()
	public ResponseEntity<Produit> save(@RequestBody Produit produit){
	 	return ResponseEntity.ok(repository.save(produit));
	}
	@PutMapping()
	public ResponseEntity<Produit> update(@RequestBody Produit produit){
	 	return ResponseEntity.ok(repository.save(produit));
	}
	@DeleteMapping()
	public ResponseEntity<Produit> delete(@RequestBody Produit produit){
	 	repository.delete(produit); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Produit>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Produit>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
