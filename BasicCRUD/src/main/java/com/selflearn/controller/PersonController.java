package com.selflearn.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.selflearn.model.Person;
import com.selflearn.service.PersonsService;
/**
 * @author SONY
 *
 */
//mark class as Controller
@RestController
public class PersonController {
//autowire the PersonsService class
	@Autowired
	PersonsService personsService;

//creating a get mapping that retrieves all the people detail from the database
	//http://localhost:8080/person
	@GetMapping("/person")
	public List<Person> getAllPersons() {
		return personsService.getAllPersons();
	}

//creating a get mapping that retrieves the detail of a specific person
	@GetMapping("/person/{personId}")
	public Person getperson(@PathVariable("personId") Long personId) {
		return personsService.getpersonById(personId);
	}

//creating a delete mapping that deletes a specified person
	@DeleteMapping("/person/{personId}")
	public void deletePerson(@PathVariable("personId") Long personId) {
		personsService.delete(personId);
	}

//creating post mapping that post the person detail in the database
	//http://localhost:8080/personIds
	@PostMapping("/personIds")
	public Long savePerson(@RequestBody Person person) {
		personsService.saveOrUpdate(person);
		return person.getId();
	}

//creating put mapping that updates the person detail 
	@PutMapping("/persons")
	public Person update(@RequestBody Person person) {
		personsService.saveOrUpdate(person);
		return person;
	}
	
	//creating a get mapping that retrieves the detail of a specific person
		@GetMapping("/personcount")
		public long getpersonCount() {
			return personsService.getCount();
		}
}
