/**
 * 
 */
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

import com.selflearn.model.Address;
import com.selflearn.model.Person;
import com.selflearn.service.AddressService;
import com.selflearn.service.PersonsService;

/**
 * @author SONY
 *
 */
@RestController
public class AddressController {
	@Autowired
	AddressService addressService;
	
	@Autowired
	PersonsService personsService;

	// http://localhost:8080/address
	@GetMapping("/address")
	private List<Address> getAllAddress() {
		return addressService.getAllAddress();
	}

	// creating post mapping that post the address detail in the database
	// http://localhost:8080/addressIds
	@PostMapping("/saveAddress/{person_id}")
	private String saveAddress(@RequestBody Address address, @PathVariable long person_id) {
		String result = null;
		try {
			Person person = personsService.getpersonById(person_id);
			 if(person != null) {
				address.setPerson(person);
				addressService.saveOrUpdate(address);
				result = "Successfully added address to Person";
			}else {
				result = "No person with particular id";
			}
		}catch (Exception ex){
            ex.printStackTrace();
            return "Error in saving Address ..";
        }
		return result;
	}

	// creating put mapping that updates the address detail
	@PutMapping("/updateaddress")
	private Address update(@RequestBody Address address) {
		addressService.saveOrUpdate(address);
		return address;
	}

	// creating a delete mapping that deletes a specified address
	@DeleteMapping("/address/{address_id}")
	private void deleteAddress(@PathVariable("address_id") Long address_id) {
		addressService.delete(address_id);
	}

}
