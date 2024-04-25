package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.TransactionMatiereRepository;
import com.tsakitsaky.mg.entity.TransactionMatiere;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "transactionMatiere")
public class TransactionMatiereController {

	@Autowired
	private TransactionMatiereRepository repository;


	@PostMapping()
	public ResponseEntity<TransactionMatiere> save(@RequestBody TransactionMatiere transactionMatiere){
	 	return ResponseEntity.ok(repository.save(transactionMatiere));
	}
	@PutMapping()
	public ResponseEntity<TransactionMatiere> update(@RequestBody TransactionMatiere transactionMatiere){
	 	return ResponseEntity.ok(repository.save(transactionMatiere));
	}
	@DeleteMapping()
	public ResponseEntity<TransactionMatiere> delete(@RequestBody TransactionMatiere transactionMatiere){
	 	repository.delete(transactionMatiere); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<TransactionMatiere>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<TransactionMatiere>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
