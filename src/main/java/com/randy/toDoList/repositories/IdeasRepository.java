package com.randy.toDoList.repositories;

import org.springframework.data.repository.CrudRepository;

import com.randy.toDoList.models.Ideas;


public interface IdeasRepository extends CrudRepository <Ideas, Long>{

}
