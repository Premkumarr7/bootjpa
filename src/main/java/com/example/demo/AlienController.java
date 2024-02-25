package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo; 
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	} 
	
	@DeleteMapping("/alien/{aid}")
	public String deletedAlien(@PathVariable int aid) {
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "Delete";
	}
	
	@PostMapping(path="/alien",consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@PutMapping(path="/alien",consumes= {"application/json"})
	public Alien saveOrUpdatedAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@GetMapping(path="/aliens",produces= {"application/xml"})
	public List<Alien> get() {
		
		return repo.findAll();
	}
	@GetMapping(path="/alien/{aid}",produces= {"application/xml"})
	public Optional<Alien> getAliens(@PathVariable int aid) {
		
		return repo.findById(aid);
	}

}
