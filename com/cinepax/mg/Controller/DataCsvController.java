package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.DataCsvRepository;
import com.cinepax.mg.entity.DataCsv;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "dataCsv")
public class DataCsvController {

	@Autowired
	private DataCsvRepository repository;


	@PostMapping()
	public ResponseEntity<DataCsv> save(@RequestBody DataCsv dataCsv){
	 	return ResponseEntity.ok(repository.save(dataCsv));
	}
	@PutMapping()
	public ResponseEntity<DataCsv> update(@RequestBody DataCsv dataCsv){
	 	return ResponseEntity.ok(repository.save(dataCsv));
	}
	@DeleteMapping()
	public ResponseEntity<DataCsv> delete(@RequestBody DataCsv dataCsv){
	 	repository.delete(dataCsv); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<DataCsv>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<DataCsv>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}
}
