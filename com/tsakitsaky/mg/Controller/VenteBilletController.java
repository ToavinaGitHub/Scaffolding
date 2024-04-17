package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.VenteBilletRepository;
import com.tsakitsaky.mg.entity.VenteBillet;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "venteBillet")
public class VenteBilletController {

	@Autowired
	private VenteBilletRepository repository;


	@PostMapping()
	public ResponseEntity<VenteBillet> save(@RequestBody VenteBillet venteBillet){
	 	return ResponseEntity.ok(repository.save(venteBillet));
	}
	@PutMapping()
	public ResponseEntity<VenteBillet> update(@RequestBody VenteBillet venteBillet){
	 	return ResponseEntity.ok(repository.save(venteBillet));
	}
	@DeleteMapping()
	public ResponseEntity<VenteBillet> delete(@RequestBody VenteBillet venteBillet){
	 	repository.delete(venteBillet); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<VenteBillet>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<VenteBillet>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
