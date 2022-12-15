package com.sonia.demo.controller;

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
import com.sonia.demo.dao.AlienRepo;
import com.sonia.demo.model.Alien;

//@Controller
@RestController // means all the controller will be working for rest api and we dont need to use
				// //@ResponseBody for every method
public class AlienController {

	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		repo.save(alien);
		return "getAlien";
	}

//	@RequestMapping("/getAlien")
//	public ModelAndView getAlien() {
//		ModelAndView mv = new ModelAndView("showAlien.jsp");
//		Iterable<Alien> aliens = repo.findAll();
//		mv.addObject("aliens", aliens);
//		//System.out.println(aliens);
//		return mv;
//	}

	// Using rest url here
	@GetMapping("/aliens") // -- this will bydefault support both json and xml
	// @RequestMapping(path="/aliens",produces = {"application/xml"}) // it will
	// restrict to xml

	// @ResponseBody
	// public String getAliens()
	public List<Alien> getAliens() {
		// return repo.findAll().toString();
		// here data will return in json format // here jackson dependency id
		// responsible for conevrting java obj into json // bydefault convert to json //
		// for converting to xml we need to add dependecy jackson dataformat xml
		// After adding the xml dependecy you can get data in xml and json both whatever
		// option you select in postman
		return repo.findAll();
	}

	// Using rest url here
	@GetMapping("/alien/{aid}")
	// @ResponseBody
	// public String getAlien(@PathVariable("aid") int aid)
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		// return repo.findById(aid).toString();
		// here data will return in json format // here jackson dependency id
		// responsible for conevrting java obj into json // bydefault convert to json //
		// for converting to xml we need to add dependecy jackson dataformat xml
		// After adding the xml dependecy you can get data in xml and json both whatever
		// option you select in postman

		return repo.findById(aid);
	}

	@PostMapping("/aliens")
	// @ResponseBody
	// public Alien saveAlien(Alien alien)
	public Alien saveAlien(@RequestBody Alien alien) // we need to add @RequestBosy for enable raw data adding in body
														// in postman
	{
		repo.save(alien);
		return alien;
	}

	@DeleteMapping("/alien/{aid}")
	public String deleteAll(@PathVariable("aid") int aid) {

		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "Deleted";

	}
	
	@PutMapping("/alien")   // We can use post(only will Update when data available) also but Put will save if data not available and update only when data will available
	public Alien saveOrupdateAlien(@RequestBody Alien alien) // we need to add @RequestBosy for enable raw data adding in body
														// in postman
	{
		repo.save(alien);
		return alien;
	}

	@RequestMapping("/deleteAlien")
	public String deleteAlien(@RequestParam("id") int id) {
		repo.deleteById(id);
		return "getAlien";
	}

	@RequestMapping("/editAlien")
	public ModelAndView updateAlien(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("editform.jsp");
		Optional<Alien> alien = repo.findById(id);
		mv.addObject("alien", alien);
		return mv;
	}

	@RequestMapping("/getByField")
	public ModelAndView getAlienByField() {
		ModelAndView mv = new ModelAndView("getAlienByField.jsp");
		// List<Alien> aliens = repo.findByTech("Java");
		// List<Alien> aliens =repo.findByAidGreaterThan(3);
		// List<Alien> aliens = repo.findByAidLessThan(3);
		List<Alien> aliens = repo.findByTechSorted("Java");
		mv.addObject("aliens", aliens);
		System.out.println(aliens);
		return mv;
	}

}
