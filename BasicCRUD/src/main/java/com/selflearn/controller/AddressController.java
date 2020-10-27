/**
 * 
 */
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

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

	// http://localhost:8080/address
	@GetMapping("/address")
	public List<Address> getAllAddress() {
		logger.info("Address -> getAllAddress()");
		return addressService.getAllAddress();
	}

	// creating post mapping that post the address detail in the database
	// http://localhost:8080/addressIds
	@PostMapping("/saveAddress/{person_id}")
	public String saveAddress(@RequestBody Address address, @PathVariable long person_id) {
		String result = null;
		logger.info("Address -> saveAddress()");
		Address returnedAddress = saveOrEditAddress(address, person_id);
		logger.info("Person from DB :" + returnedAddress.getAddress_id());
		try {
			if (returnedAddress.getPerson() != null) {
				addressService.saveOrUpdate(returnedAddress);
				result = "Successfully added address to Person";
				logger.info("Successfully added address to Person");
			} else {
				result = "No person with particular id";
				logger.info("No person with particular id");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			return "Error in saving Address ..";
		}
		return result;
	}

	// creating put mapping that updates the address detail
	@PutMapping("/editaddress/{person_id}")
	public Address editAddress(@RequestBody Address address, @PathVariable long person_id) {
		logger.info("Address -> editAddress()");
		Address updatedAddress = null;
		Address returnedAddress = saveOrEditAddress(address, person_id);
		logger.info("Person from DB :" + returnedAddress.getAddress_id());
		try {
			if (returnedAddress.getPerson() != null) {
				updatedAddress = addressService.saveOrUpdate(returnedAddress);
				logger.info("Successfully added address to Person");
			} else {
				logger.info("No person with particular id");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage(), ex);
		}
		return updatedAddress;
	}

	// creating a delete mapping that deletes a specified address
	@DeleteMapping("/address/{address_id}")
	public void deleteAddress(@PathVariable("address_id") Long address_id) {
		logger.info("Address -> deleteAddress()");
		addressService.delete(address_id);
	}

	private Address saveOrEditAddress(Address address, long person_id) {
		logger.info("Address -> saveOrEditAddress()");
		Person person = personsService.getpersonById(person_id);
		if (person != null) {
			logger.info("Address -> Person with ID is present");
			address.setPerson(person);
		} else {
			logger.info("Cannot Add Address to DB");
			logger.error("Cannot Add Address to DB");
		}
		return address;
	}

}
