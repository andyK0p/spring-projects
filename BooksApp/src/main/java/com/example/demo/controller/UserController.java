package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.mongodb.client.result.DeleteResult;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	//###### USER REST SERVICES #######
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();
		
		if(users == null)
        	return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
        else
        	return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByName(@RequestParam(value="name", defaultValue="World") String name) {
        User user = userService.getUserByName(name);
                
        if(user == null) 
        	return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
        
        else 
        	return new ResponseEntity<User>(user, HttpStatus.OK);
        
    }
		
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<User> addUser( @RequestBody User newUser){
		
		User user = userService.addUser(newUser);
		
		if(user == null) 			
        	return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		
        else 
        	return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable String id,  @RequestBody User newUser) {
		User user = userService.updateUserById(id, newUser);
		if(user == null) 			
        	return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		
        else 
        	return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public ResponseEntity<DeleteResult> deleteUserByName(@RequestParam(value="name", defaultValue="Anonymous") String name) {
        DeleteResult res = userService.deleteUserByName(name);
                       
        if(res == null) 
        	return new ResponseEntity<DeleteResult>(res, HttpStatus.NO_CONTENT);
        
        else 
        	return new ResponseEntity<DeleteResult>(res, HttpStatus.OK);
        
    }
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DeleteResult> deleteUserById(@PathVariable String id) {
        DeleteResult res = userService.deleteUserById(id);
               
        if(res == null) 
        	return new ResponseEntity<DeleteResult>(res, HttpStatus.NO_CONTENT);
        
        else 
        	return new ResponseEntity<DeleteResult>(res, HttpStatus.OK);   
    }
	
}
