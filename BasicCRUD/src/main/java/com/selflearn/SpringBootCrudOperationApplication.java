package com.selflearn;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.selflearn.model.Address;
import com.selflearn.model.Person;
import com.selflearn.repository.PersonRepository;
/**
 * @author SONY
 *
 */
@SpringBootApplication
public class SpringBootCrudOperationApplication implements CommandLineRunner{
	@Autowired
	private PersonRepository personRepository;

	private static final Logger logger = LoggerFactory.getLogger(SpringBootCrudOperationApplication.class);
	
	public static void main(String[] args) {

		SpringApplication.run(SpringBootCrudOperationApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		logger.info("In Overriden run method");
	    //Bi-directional mapping
	  Person person1 = new Person();
	  person1.setFirstName("Sam");
	  person1.setLastName("Currain");
	  //Addresss list
	  Address address = new Address();
	  address.setAddress_id(11l);
	  address.setCity("HYD");
	  address.setStreet("Yellow Lane");
	  address.setState("TELANGANA");
	  address.setPostalCode("Z00105");
	  address.setPerson(person1);
	  
	  Address address1 = new Address();
	  address1.setAddress_id(12l);
	  address1.setCity("HYD");
	  address1.setStreet("Yellow Lane");
	  address1.setState("TELANGANA");
	  address1.setPostalCode("Z00105");
	  address1.setPerson(person1);
	  Set<Address> addressLst= new HashSet<Address>();
	  addressLst.add(address);
	  addressLst.add(address1);
	  person1.setAddressList(addressLst);
	  Person retPerson = personRepository.save(person1);
	  logger.info("Persisted first person details" +retPerson.getId());
}
}
