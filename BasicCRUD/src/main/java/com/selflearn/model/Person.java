package com.selflearn.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
/**
 * @author SONY
 *
 */
//mark class as an Entity 
@Entity
@Table(name = "PERSON")
public class Person {
//Defining id as primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	
	@OneToMany(mappedBy = "person")
	@JsonManagedReference
	private Set<Address> addressList = new HashSet<Address>();
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the addressList
	 */
	public Set<Address> getAddressList() {
		return addressList;
	}

	/**
	 * @param addressList the addressList to set
	 */
	public void setAddressList(Set<Address> addressList) {
		this.addressList = addressList;
	}
		
	public boolean removeAddress(Address address) {
		return this.addressList.remove(address);
	}

}
