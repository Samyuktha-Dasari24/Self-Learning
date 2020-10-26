/**
 * 
 */
package com.selflearn.service;

import java.util.List;

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

	// getting all persons record by using the method findaAll() of JPARepository
	public List<Address> getAllAddress() {
		//List<Address> address = new ArrayList<Address>();
		return addressRepository.findAll();
				//.forEach(address1 -> address.add(address1));
		}

	// saving a specific record by using the method save() of JPARepository
	public Address saveOrUpdate(Address address) {
		Address updatedAddress = addressRepository.save(address);
		return updatedAddress;
	}

	// deleting a specific record by using the method deleteById() of JPARepository
	public void delete(Long id) {
		Address address = addressRepository.getOne(id);
		if(address != null) {
			if(address.getPerson() != null) {
				address.setPerson(null);
				System.out.println("Address with id is found and about to delete");
				addressRepository.delete(address);
			}
		}else {
			System.out.println("No address with id is found");
		}
			}
}
