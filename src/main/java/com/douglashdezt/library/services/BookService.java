package com.douglashdezt.library.services;

import java.util.List;

import com.douglashdezt.library.models.entities.Book;

public interface BookService {
	/*
	 * Insert, delete, getOneByid, getAll
	 * */
	
	void insert(Book book);
	void delete(String isbn);
	Book getOneById(String isbn);
	List <Book> getAll();
	List <String> getAllIsbns();
}
