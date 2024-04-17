package com.tsakitsaky.mg.Controller;


import com.tsakitsaky.mg.Repository.DetailPackRepository;
import com.tsakitsaky.mg.entity.DetailPack;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "detailPack")
public class DetailPackController {

	@Autowired
	private DetailPackRepository repository;


	@PostMapping()
	public ResponseEntity<DetailPack> save(@RequestBody DetailPack detailPack){
	 	return ResponseEntity.ok(repository.save(detailPack));
	}
	@PutMapping()
	public ResponseEntity<DetailPack> update(@RequestBody DetailPack detailPack){
	 	return ResponseEntity.ok(repository.save(detailPack));
	}
	@DeleteMapping()
	public ResponseEntity<DetailPack> delete(@RequestBody DetailPack detailPack){
	 	repository.delete(detailPack); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<DetailPack>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<DetailPack>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
