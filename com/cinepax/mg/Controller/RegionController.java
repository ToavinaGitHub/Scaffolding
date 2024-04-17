package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.RegionRepository;
import com.cinepax.mg.entity.Region;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "region")
public class RegionController {

	@Autowired
	private RegionRepository repository;


	@PostMapping()
	public ResponseEntity<Region> save(@RequestBody Region region){
	 	return ResponseEntity.ok(repository.save(region));
	}
	@PutMapping()
	public ResponseEntity<Region> update(@RequestBody Region region){
	 	return ResponseEntity.ok(repository.save(region));
	}
	@DeleteMapping()
	public ResponseEntity<Region> delete(@RequestBody Region region){
	 	repository.delete(region); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Region>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Region>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
