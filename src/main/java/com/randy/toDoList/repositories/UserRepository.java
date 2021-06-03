package com.randy.toDoList.repositories;

import org.springframework.data.repository.CrudRepository;

import com.randy.toDoList.models.User;



public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);

}
