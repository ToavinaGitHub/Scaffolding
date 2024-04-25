package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.DetailsVenteBilletRepository;
import com.cinepax.mg.entity.DetailsVenteBillet;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "detailsVenteBillet")
public class DetailsVenteBilletController {

	@Autowired
	private DetailsVenteBilletRepository repository;


	@PostMapping()
	public ResponseEntity<DetailsVenteBillet> save(@RequestBody DetailsVenteBillet detailsVenteBillet){
	 	return ResponseEntity.ok(repository.save(detailsVenteBillet));
	}
	@PutMapping()
	public ResponseEntity<DetailsVenteBillet> update(@RequestBody DetailsVenteBillet detailsVenteBillet){
	 	return ResponseEntity.ok(repository.save(detailsVenteBillet));
	}
	@DeleteMapping()
	public ResponseEntity<DetailsVenteBillet> delete(@RequestBody DetailsVenteBillet detailsVenteBillet){
	 	repository.delete(detailsVenteBillet); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<DetailsVenteBillet>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<DetailsVenteBillet>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
