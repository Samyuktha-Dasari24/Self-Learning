package com.selflearn.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selflearn.model.Person;
import com.selflearn.repository.PersonRepository;

//defining the business logic
@Service
public class PersonsService {
	@Autowired
	PersonRepository personRepository;

//getting all persons record by using the method findaAll() of CrudRepository
	public List<Person> getAllPersons() {
		List<Person> person = new ArrayList<Person>();
		personRepository.findAll().forEach(person1 -> person.add(person1));
		return person;
	}

//getting a specific record by using the method findById() of CrudRepository
	public Person getpersonById(int id) {
		return personRepository.findById(id).get();
	}

//saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(Person person) {
		personRepository.save(person);
	}

//deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(int id) {
		personRepository.deleteById(id);
	}

//updating a record
	public void update(Person person, int personid) {
		personRepository.save(person);
	}
}
