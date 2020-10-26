package com.selflearn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.selflearn.controller.AddressController;
import com.selflearn.controller.PersonController;
import com.selflearn.model.Address;
import com.selflearn.model.Person;
/**
 * @author SONY
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCrudOperationApplicationTests {

	@BeforeEach
	void loadData() {
		Address address = new Address();
		address.setAddress_id(105l);
		address.setCity("HYD");
		address.setStreet("Yellow Lane");
		address.setState("TELANGANA");
		address.setPostalCode("Z00104");

		Address address1 = new Address();
		address1.setAddress_id(106l);
		address1.setCity("HYD");
		address1.setStreet("Yellow Lane");
		address1.setState("TELANGANA");
		address1.setPostalCode("Z00106");
		addressController.saveAddress(address, 1l);
		addressController.saveAddress(address1, 1l);

		Person person1 = new Person();
		person1.setFirstName("Benedict");
		person1.setLastName("Adam");
		Set<Address> list = new HashSet<Address>() ;
		list.add(address);
		list.add(address1);
		person1.setAddressList(list);
		personController.savePerson(person1);
	}

	@Autowired
	private AddressController addressController;

	@Autowired
	private PersonController personController;

	@Test
	void contextLoads() {

	}

	// Get all addresses
	@Test
	public void testGetAllAddress() {
		List<Address> addressLst = new ArrayList<>();
		addressLst = addressController.getAllAddress();
		assertEquals(3, addressLst.size());
	}

	// Save Address when Person is available in DB
	@Test
	public void testSavaAddressWithPerson() {
		Address address = new Address();
		address.setAddress_id(104l);
		address.setCity("HYD");
		address.setStreet("Yellow Lane");
		address.setState("TELANGANA");
		address.setPostalCode("Z00104");

		Address address1 = new Address();
		address1.setAddress_id(105l);
		address1.setCity("HYD");
		address1.setStreet("Red Lane");
		address1.setState("TELANGANA");
		address1.setPostalCode("Z00105");

		String result = addressController.saveAddress(address, 1l);
		assertEquals("Successfully added address to Person", result);
	}

	// Person is not available in DB
	@Test
	public void testSavaAddressWithoutPerson() {
		Address address = new Address();
		address.setAddress_id(104l);
		address.setCity("HYD");
		address.setStreet("Yellow Lane");
		address.setState("TELANGANA");
		address.setPostalCode("Z00104");
		String result = addressController.saveAddress(address, 6l);
		assertEquals("No person with particular id", result);
	}

	// Update existing Address
	@Test
	public void testEditAddressWithPerson() {
		Address address = new Address();
		address.setAddress_id(104l);
		address.setCity("HYD");
		address.setStreet("Blue Lane");
		address.setState("TELANGANA");
		address.setPostalCode("Z00104");
		Address updatedAddress = addressController.editAddress(address, 1l);
		assertEquals("Blue Lane", updatedAddress.getStreet());
	}

	// Delete Address which is not present in DB
	@Test
	public void testDeleteAddress() {
		addressController.deleteAddress(105l);
	}

	@Test
	public void testGetAllPersons() {
		List<Person> personLst = personController.getAllPersons();
		for (Person person : personLst) {
			System.out.println("Person Id: " +person.getId()+ " " + "person First name: " + person.getFirstName()+ "Person Last Name: " +person.getLastName());
		}
		assertEquals("Sam", personLst.get(0).getFirstName());
	}

	@Test
	public void testGetPersonCount() {
		Long count = personController.getpersonCount();
		assertEquals(14l, count);
	}

	@Test
	public void testSavePerson() {
		Person person1 = new Person();
		person1.setFirstName("Thomas");
		person1.setLastName("Grey");
		Long id = personController.savePerson(person1);
		assertEquals(7l, id);

	}
	@Test
	public void testDeletePerson() {
		personController.deletePerson(2l);
	}
	
	@Test
	public void testEditPerson() {
		Person person1 = new Person();
		person1.setFirstName("Sam");
		person1.setLastName("Grey");
		person1 = personController.update(person1);
		assertEquals(11l, person1.getId());
		
	}
	
	@Test
	public void testGetPerson() {
		Person person = personController.getperson(3l);
		assertEquals("Adam", person.getLastName());
	}
}
