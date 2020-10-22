package com.selflearn.repository;
import org.springframework.data.repository.CrudRepository;

import com.selflearn.model.Person;
public interface PersonRepository extends CrudRepository<Person, Integer>
{
}
