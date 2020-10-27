package com.selflearn.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selflearn.model.Address;
import com.selflearn.model.Person;
import com.selflearn.repository.PersonRepository;

/**
 * @author SONY
 *
 */
//defining the business logic
@Service
public class PersonsService {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	AddressService addressservice;

	private static final Logger logger = LoggerFactory.getLogger(PersonsService.class);

	// getting all persons record by using the method findaAll() of CrudRepository
	public List<Person> getAllPersons() {
		logger.info("Person -> getAllPersons()");
		return personRepository.findAll();

	}

//getting a specific record by using the method findById() of CrudRepository
	public Person getpersonById(Long personId) {
		logger.info("Person -> getpersonById() : " + personId);
		return personRepository.findPersonById(personId);
	}

//saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(Person person) {
		logger.info("Person -> saveOrUpdate() : " + person.getFirstName());
		personRepository.save(person);
		logger.info("Person -> saveOrUpdate() : " + person.getId());
	}

//deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(Long id) {
		logger.info("Person -> delete() : " + id);
		try {
			Person person = personRepository.getOne(id);
			logger.info("Person from DB : " + person.getId());
			if (person.getAddressList().isEmpty()) {
				logger.error("Deleting Person from DB as no adddress tied to him");
				personRepository.delete(person);
			} else {
				logger.info("Deleting Address related to Person from Table: Size of addressList :"
						+ person.getAddressList().size());
				for (Address address : person.getAddressList()) {
					addressservice.delete(address.getAddress_id());
				}
				logger.info("Deleting Person from DB after clearing adddress tied to him");
				personRepository.delete(person);
			}
		} catch (EntityNotFoundException ex) {
			 ex.printStackTrace();
	           logger.error(ex.getMessage(), ex);
		}

	}

//updating a record
	public void update(Person person, long personid) {
		logger.info("Person -> update() : " + personid);
		personRepository.save(person);
	}

	// get count
	public long getCount() {
		logger.info("Person -> getCount()");
		long count = 0l;
		count = personRepository.count();
		logger.info("Person -> Count of Persons : " +count);
		return count;
	}

}
