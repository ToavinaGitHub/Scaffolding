package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.PackRepository;
import com.tsakitsaky.mg.entity.Pack;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "pack")
public class PackController {

	@Autowired
	private PackRepository repository;


	@PostMapping()
	public ResponseEntity<Pack> save(@RequestBody Pack pack){
	 	return ResponseEntity.ok(repository.save(pack));
	}
	@PutMapping()
	public ResponseEntity<Pack> update(@RequestBody Pack pack){
	 	return ResponseEntity.ok(repository.save(pack));
	}
	@DeleteMapping()
	public ResponseEntity<Pack> delete(@RequestBody Pack pack){
	 	repository.delete(pack); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Pack>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Pack>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
