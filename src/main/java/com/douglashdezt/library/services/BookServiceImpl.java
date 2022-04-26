package com.douglashdezt.library.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.douglashdezt.library.models.entities.Book;
import com.douglashdezt.library.services.utils.ServiceResponse;

@Service
public class BookServiceImpl implements BookService {
	
	private static List<Book> library = new ArrayList<>();
    static {
        library.add(new Book("0261102303", "Lord of the Rings"));
        library.add(new Book("0007441428", "Game of Thrones"));
        library.add(new Book("0747581088", "Harry Potter and the Half-Blood Prince"));
        library.add(new Book("1401248195", "Watchmen"));
        library.add(new Book("030788743X", "Ready player one"));
        library.add(new Book("843760494X", "Cien Años de Soledad"));
        library.add(new Book("0553804367", "A Briefer History of Time"));
    }

	@Override
	public ServiceResponse<Void> insert(Book book) {
		try {
			library.add(book);
			return new ServiceResponse<>(true);
		} catch (Exception e) {
			return new ServiceResponse<>(false);
		}
	}

	@Override
	public ServiceResponse<Void> delete(String isbn) {
		try {
			library = library.stream()
					.filter(book -> !book.getIsbn().equals(isbn))
					.toList();
			return new ServiceResponse<>(true);
		} catch (Exception e) {
			return new ServiceResponse<>(false);
		}
	}

	@Override
	public ServiceResponse<Book> getOneById(String isbn) {
		Book foundBook = library.stream()
				.filter((book)-> book.getIsbn().equals(isbn))
				.findAny()
				.orElse(null);
		
		if(foundBook == null) {
			return new ServiceResponse<>(false);
		}
			
		return new ServiceResponse<>(true, foundBook);
	}

	@Override
	public ServiceResponse<List<Book>> getAll() {
		return new ServiceResponse(new ArrayList<Book>(library));
	}

	@Override
	public ServiceResponse<List<String>> getAllIsbns() {
		List<String> isbns = library
				.stream()
				.map((book)-> {
					return book.getIsbn();
				})
				.toList();
		return new ServiceResponse<>(isbns);
	}

}
