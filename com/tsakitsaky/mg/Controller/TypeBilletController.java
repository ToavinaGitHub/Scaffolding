package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.TypeBilletRepository;
import com.tsakitsaky.mg.entity.TypeBillet;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "typeBillet")
public class TypeBilletController {

	@Autowired
	private TypeBilletRepository repository;


	@PostMapping()
	public ResponseEntity<TypeBillet> save(@RequestBody TypeBillet typeBillet){
	 	return ResponseEntity.ok(repository.save(typeBillet));
	}
	@PutMapping()
	public ResponseEntity<TypeBillet> update(@RequestBody TypeBillet typeBillet){
	 	return ResponseEntity.ok(repository.save(typeBillet));
	}
	@DeleteMapping()
	public ResponseEntity<TypeBillet> delete(@RequestBody TypeBillet typeBillet){
	 	repository.delete(typeBillet); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<TypeBillet>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<TypeBillet>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
