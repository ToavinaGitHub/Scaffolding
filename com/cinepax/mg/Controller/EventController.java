package com.cinepax.mg.Controller;


import com.cinepax.mg.Repository.EventRepository;
import com.cinepax.mg.entity.Event;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@RestController
@CrossOrigin("*")
@RequestMapping(path = "event")
public class EventController {

	@Autowired
	private EventRepository repository;


	@PostMapping()
	public ResponseEntity<Event> save(@RequestBody Event event){
	 	return ResponseEntity.ok(repository.save(event));
	}
	@PutMapping()
	public ResponseEntity<Event> update(@RequestBody Event event){
	 	return ResponseEntity.ok(repository.save(event));
	}
	@DeleteMapping()
	public ResponseEntity<Event> delete(@RequestBody Event event){
	 	repository.delete(event); return ResponseEntity.ok().build();
	}
	@GetMapping(path = "" , params = {"page"})
	public ResponseEntity<Iterable<Event>> findAll(@RequestParam(name="page") int page){
	 Pageable paging = PageRequest.of(page , 3);
		return ResponseEntity.ok(repository.findAll(paging));
	}
	@GetMapping()
	public ResponseEntity<Iterable<Event>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}





}
