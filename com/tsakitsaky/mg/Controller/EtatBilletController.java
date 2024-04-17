package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.EtatBilletRepository;
import com.tsakitsaky.mg.entity.EtatBillet;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "etatBillet")
public class EtatBilletController {

	@Autowired
	private EtatBilletRepository repository;


	@PostMapping()
	public ResponseEntity<EtatBillet> save(@RequestBody EtatBillet etatBillet){
	 	return ResponseEntity.ok(repository.save(etatBillet));
	}
	@PutMapping()
	public ResponseEntity<EtatBillet> update(@RequestBody EtatBillet etatBillet){
	 	return ResponseEntity.ok(repository.save(etatBillet));
	}
	@DeleteMapping()
	public ResponseEntity<EtatBillet> delete(@RequestBody EtatBillet etatBillet){
	 	repository.delete(etatBillet); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<EtatBillet>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<EtatBillet>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
