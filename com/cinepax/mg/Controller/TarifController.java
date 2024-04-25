package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.TarifRepository;
import com.cinepax.mg.entity.Tarif;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "tarif")
public class TarifController {

	@Autowired
	private TarifRepository repository;


	@PostMapping()
	public ResponseEntity<Tarif> save(@RequestBody Tarif tarif){
	 	return ResponseEntity.ok(repository.save(tarif));
	}
	@PutMapping()
	public ResponseEntity<Tarif> update(@RequestBody Tarif tarif){
	 	return ResponseEntity.ok(repository.save(tarif));
	}
	@DeleteMapping()
	public ResponseEntity<Tarif> delete(@RequestBody Tarif tarif){
	 	repository.delete(tarif); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Tarif>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Tarif>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
