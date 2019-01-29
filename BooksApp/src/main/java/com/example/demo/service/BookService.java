package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.mongodb.client.result.DeleteResult;

@Service
public class BookService {
	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<Book> getAll() {
		return mongoTemplate.findAll(Book.class);	
	}
	
	public Book getBookByTitle(String title) {
		Query query = new Query();
		query.addCriteria(Criteria.where("title").is(title));
		Book book = mongoTemplate.findOne(query, Book.class);
		
		return book;
	}
	
	public Book addBook(Book book) {
		return mongoTemplate.save(book);		
	}
	
	public Book updateBookById(String id, Book update) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Book book = mongoTemplate.findOne(query, Book.class);
		if(book != null) {
			if(update.getTitle() != null)
				book.setTitle(update.getTitle());
			if(update.getAuthor() != null)
				book.setAuthor(update.getAuthor());
			if(update.getYear() != null)
				book.setYear(update.getYear());
			return mongoTemplate.save(book);
		}
		else {
			update.setId(id);
			return mongoTemplate.save(update);
		}		
	}
	
	public DeleteResult deleteBookByTitle(String title) {
		Query query = new Query();
		query.addCriteria(Criteria.where("title").is(title));
		return mongoTemplate.remove(query, Book.class);					
	}
	
	public DeleteResult deleteBookById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.remove(query, Book.class);					
	}
}
