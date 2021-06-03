package com.randy.toDoList.repositories;

import org.springframework.data.repository.CrudRepository;

import com.randy.toDoList.models.User;
import com.randy.toDoList.models.UserLikedIdeas;

public interface UserLikedIdeasRepository extends CrudRepository <UserLikedIdeas, Long>{


}
