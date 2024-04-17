package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.PayementRepository;
import com.tsakitsaky.mg.entity.Payement;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "payement")
public class PayementController {

	@Autowired
	private PayementRepository repository;


	@PostMapping()
	public ResponseEntity<Payement> save(@RequestBody Payement payement){
	 	return ResponseEntity.ok(repository.save(payement));
	}
	@PutMapping()
	public ResponseEntity<Payement> update(@RequestBody Payement payement){
	 	return ResponseEntity.ok(repository.save(payement));
	}
	@DeleteMapping()
	public ResponseEntity<Payement> delete(@RequestBody Payement payement){
	 	repository.delete(payement); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Payement>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Payement>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
