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

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.mongodb.client.result.DeleteResult;

@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	//##### BOOK REST SERVICES #####
		@RequestMapping(value = "/books", method = RequestMethod.GET)
		public ResponseEntity<List<Book>> getAllBooks() {
			List<Book> books = bookService.getAll();
			
			if(books == null)
	        	return new ResponseEntity<List<Book>>(books, HttpStatus.NO_CONTENT);
	        else
	        	return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/book", method = RequestMethod.GET)
	    public ResponseEntity<Book> getBookByTitle(@RequestParam(value="title", defaultValue="none") String title) {
			Book book = bookService.getBookByTitle(title);
	                
	        if(book == null) 
	        	return new ResponseEntity<Book>(book, HttpStatus.NO_CONTENT);
	        
	        else 
	        	return new ResponseEntity<Book>(book, HttpStatus.OK);
	        
	    }
			
		@RequestMapping(value = "/book", method = RequestMethod.POST)
		public ResponseEntity<Book> addBook( @RequestBody Book newBook){
			
			Book book = bookService.addBook(newBook);
			
			if(book == null) 			
	        	return new ResponseEntity<Book>(book, HttpStatus.NO_CONTENT);
			
	        else 
	        	return new ResponseEntity<Book>(book, HttpStatus.CREATED);
		}
		
		@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
		public ResponseEntity<Book> updateBook(@PathVariable String id,  @RequestBody Book newBook) {
			Book book = bookService.updateBookById(id, newBook);
			if(book == null) 			
	        	return new ResponseEntity<Book>(book, HttpStatus.NO_CONTENT);
			
	        else 
	        	return new ResponseEntity<Book>(book, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/book", method = RequestMethod.DELETE)
	    public ResponseEntity<DeleteResult> deleteBookByTitle(@RequestParam(value="title", defaultValue="none") String title) {
	        DeleteResult res = bookService.deleteBookByTitle(title);
	                       
	        if(res == null) 
	        	return new ResponseEntity<DeleteResult>(res, HttpStatus.NO_CONTENT);
	        
	        else 
	        	return new ResponseEntity<DeleteResult>(res, HttpStatus.OK);
	        
	    }
		
		@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<DeleteResult> deleteBookById(@PathVariable String id) {
	        DeleteResult res = bookService.deleteBookById(id);
	               
	        if(res == null) 
	        	return new ResponseEntity<DeleteResult>(res, HttpStatus.NO_CONTENT);
	        
	        else 
	        	return new ResponseEntity<DeleteResult>(res, HttpStatus.OK);   
	    }
}
