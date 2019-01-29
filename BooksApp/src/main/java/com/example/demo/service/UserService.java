package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.example.demo.model.User;
import com.mongodb.client.result.DeleteResult;


@Service
public class UserService {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<User> getAll() {
		return mongoTemplate.findAll(User.class);	
	}
	
	public User getUserByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		User user = mongoTemplate.findOne(query, User.class);
		
		return user;
	}
	
	public User addUser(User user) {
		return mongoTemplate.save(user);		
	}
	
	public User updateUserById(String id, User update) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		User user = mongoTemplate.findOne(query, User.class);
		if(user != null) {
			if(update.getName() != null)
				user.setName(update.getName());
			if(update.getAddress() != null)
				user.setAddress(update.getAddress());
			if(update.getBooks() != null)
				user.setBooks(update.getBooks());
			return mongoTemplate.save(user);
		}
		else {
			update.setId(id);
			return mongoTemplate.save(update);
		}		
	}
	
	public DeleteResult deleteUserByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.remove(query, User.class);					
	}
	
	public DeleteResult deleteUserById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.remove(query, User.class);					
	}

}
