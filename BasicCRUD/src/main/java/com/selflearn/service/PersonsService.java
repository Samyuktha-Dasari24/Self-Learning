package com.selflearn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selflearn.model.Address;
import com.selflearn.model.Person;
import com.selflearn.repository.AddressRepository;
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

	// getting all persons record by using the method findaAll() of CrudRepository
	public List<Person> getAllPersons() {
		//List<Person> person = new ArrayList<Person>();
		return personRepository.findAll();
		
	}

//getting a specific record by using the method findById() of CrudRepository
	public Person getpersonById(Long personId) {
		return personRepository.findPersonById(personId);
	}

//saving a specific record by using the method save() of CrudRepository
	public void saveOrUpdate(Person person) {
		personRepository.save(person);
	}

//deleting a specific record by using the method deleteById() of CrudRepository
	public void delete(Long id) {
		Person person = personRepository.getOne(id);
		if(person != null) {
			if(person.getAddressList().isEmpty()) {
				System.out.println("No adddress list is added for person");
				personRepository.delete(person);
			}else {
				for(Address address : person.getAddressList()) {
					addressservice.delete(address.getAddress_id());
				}
				personRepository.delete(person);
			}
		}else {
			System.out.println("No person with given id is found");
		}
		
	}

//updating a record
	public void update(Person person, long personid) {
		personRepository.save(person);
	}
	//get count
	public long getCount() {
		long count = 0l;
		count = personRepository.count();
		return count;
	}
	
}
