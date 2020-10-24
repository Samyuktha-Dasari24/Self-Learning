package com.selflearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.selflearn.model.Person;

/**
 * @author SONY
 *
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	public Person findPersonById(Long personId);
}
