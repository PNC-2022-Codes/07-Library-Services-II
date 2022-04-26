package com.douglashdezt.library.services;

import java.util.List;

import com.douglashdezt.library.models.entities.Book;
import com.douglashdezt.library.services.utils.ServiceResponse;

public interface BookService {
	/*
	 * Insert, delete, getOneByid, getAll
	 * */
	
	ServiceResponse<Void> insert(Book book);
	ServiceResponse<Void> delete(String isbn);
	ServiceResponse<Book> getOneById(String isbn);
	ServiceResponse<List <Book>> getAll();
	ServiceResponse<List <String>> getAllIsbns();
}
