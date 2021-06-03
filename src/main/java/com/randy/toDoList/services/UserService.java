package com.randy.toDoList.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.randy.toDoList.models.Ideas;
import com.randy.toDoList.models.User;
import com.randy.toDoList.models.UserLikedIdeas;
import com.randy.toDoList.repositories.IdeasRepository;
import com.randy.toDoList.repositories.UserLikedIdeasRepository;
import com.randy.toDoList.repositories.UserRepository;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final IdeasRepository ideasRepository;
    private final UserLikedIdeasRepository userLikedIdeasRepository;

    
    public UserService(UserRepository userRepository,IdeasRepository ideasRepository,UserLikedIdeasRepository userLikedIdeasRepository) {
        this.userRepository = userRepository;
        this.ideasRepository = ideasRepository;
        this.userLikedIdeasRepository = userLikedIdeasRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.save(user);

    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public List<User> findAllUsers(){
    	return (List<User>)this.userRepository.findAll();	
    }
    
    public List<Ideas> findAllIdeas(){
    	return (List<Ideas>)this.ideasRepository.findAll();
    }

    
    public Ideas createIdeas(Ideas ideas) {
    	return this.ideasRepository.save(ideas);
    }
    
    public Ideas thisIdea(Long id) {
    	return this.ideasRepository.findById(id).orElse(null);
    }
    
    public Ideas updateIdea(Ideas ideas) {
    	return this.ideasRepository.save(ideas);
    }
    public Ideas findAIdea(Long id) {
    	return this.ideasRepository.findById(id).orElse(null);
    }
    public UserLikedIdeas createAssociation(UserLikedIdeas ulIdeas) {
    	return this.userLikedIdeasRepository.save(ulIdeas);
    }
    public User unlike(User unlike) {
    	return this.userRepository.save(unlike);
    }
    public void deleteIdea(Ideas ideas) {
    	this.ideasRepository.delete(ideas);
    }


    
}