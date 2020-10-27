/**
 * 
 */
package com.selflearn.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selflearn.model.Address;
import com.selflearn.repository.AddressRepository;

/**
 * @author SONY
 *
 */
@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;

	private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
	
	// getting all persons record by using the method findaAll() of JPARepository
	public List<Address> getAllAddress() {
		logger.info("Address -> getAllAddress()");
		return addressRepository.findAll();
		}

	// saving a specific record by using the method save() of JPARepository
	public Address saveOrUpdate(Address address) {
		logger.info("Address -> saveOrUpdate()");
		Address updatedAddress = addressRepository.save(address);
		logger.info("Address -> updatedAddress : " +updatedAddress.getAddress_id());
		return updatedAddress;
	}

	// deleting a specific record by using the method deleteById() of JPARepository
	public void delete(Long id) {
		logger.info("Address -> deleteAddress()");
		try {
		Address address = addressRepository.getOne(id);
			if(address.getPerson() != null) {
				address.setPerson(null);
				logger.info("Address with id is found and about to delete");
				addressRepository.delete(address);
			}else {
				logger.info("Address with id is not found and not able to delete");
			}
		}catch(EntityNotFoundException ex) {
			ex.printStackTrace();
	           logger.error(ex.getMessage(), ex);
		}
			}
}
