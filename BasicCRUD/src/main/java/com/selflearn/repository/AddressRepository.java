/**
 * 
 */
package com.selflearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selflearn.model.Address;
/**
 * @author SONY
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
