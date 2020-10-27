package com.selflearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

//creating a get mapping that retrieves all the people detail from the database
	//http://localhost:8080/person
	@GetMapping("/person")
	public List<Person> getAllPersons() {
		logger.info("Person -> getAllPersons");
		return personsService.getAllPersons();
	}

//creating a get mapping that retrieves the detail of a specific person
	@GetMapping("/person/{personId}")
	public Person getperson(@PathVariable("personId") Long personId) {
		logger.info("Person -> getperson for particular Id");
		return personsService.getpersonById(personId);
	}

//creating a delete mapping that deletes a specified person
	@DeleteMapping("/person/{personId}")
	public void deletePerson(@PathVariable("personId") Long personId) {
		logger.info("Person -> deletePerson for particular Id");
		personsService.delete(personId);
	}

//creating post mapping that post the person detail in the database
	//http://localhost:8080/personIds
	@PostMapping("/personIds")
	public Long savePerson(@RequestBody Person person) {
		personsService.saveOrUpdate(person);
		logger.info("Person -> savePerson and return particular Id");
		return person.getId();
	}

//creating put mapping that updates the person detail 
	@PutMapping("/editPersons")
	public Person editPersons(@RequestBody Person person) {
		personsService.saveOrUpdate(person);
		logger.info("Person -> editPersons and return person" +person.getId());
		return person;
	}
	
	//creating a get mapping that retrieves the detail of a specific person
		@GetMapping("/personcount")
		public long getpersonCount() {
			logger.info("Person -> getpersonCount and return person count");
			return personsService.getCount();
		}
}
