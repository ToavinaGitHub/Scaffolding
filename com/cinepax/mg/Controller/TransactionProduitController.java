package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.TransactionProduitRepository;
import com.cinepax.mg.entity.TransactionProduit;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "transactionProduit")
public class TransactionProduitController {

	@Autowired
	private TransactionProduitRepository repository;


	@PostMapping()
	public ResponseEntity<TransactionProduit> save(@RequestBody TransactionProduit transactionProduit){
	 	return ResponseEntity.ok(repository.save(transactionProduit));
	}
	@PutMapping()
	public ResponseEntity<TransactionProduit> update(@RequestBody TransactionProduit transactionProduit){
	 	return ResponseEntity.ok(repository.save(transactionProduit));
	}
	@DeleteMapping()
	public ResponseEntity<TransactionProduit> delete(@RequestBody TransactionProduit transactionProduit){
	 	repository.delete(transactionProduit); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<TransactionProduit>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<TransactionProduit>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
