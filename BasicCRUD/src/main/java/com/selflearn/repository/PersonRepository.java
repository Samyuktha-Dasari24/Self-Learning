package com.selflearn.repository;
import org.springframework.data.repository.CrudRepository;

import com.selflearn.model.Person;
//repository interface
public interface PersonRepository extends CrudRepository<Person, Integer>
{
}
