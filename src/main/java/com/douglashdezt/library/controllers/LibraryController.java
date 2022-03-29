package com.douglashdezt.library.controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglashdezt.library.models.dtos.BookResponseDTO;
import com.douglashdezt.library.models.dtos.BookSearchDTO;
import com.douglashdezt.library.models.entities.Book;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	private static final List<Book> library = Arrays.asList(
            new Book("0261102303", "Lord of the Rings"),
            new Book("0007441428", "Game of Thrones"),
            new Book("0747581088", "Harry Potter and the Half-Blood Prince"),
            new Book("1401248195", "Watchmen"),
            new Book("030788743X", "Ready player one"),
            new Book("843760494X", "Cien Años de Soledad"),
            new Book("0553804367", "A Briefer History of Time")
        );
	
	//@GetMapping("/")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMainPage(Model model) {
		String time = Calendar.getInstance().getTime().toString();
		model.addAttribute("time", time);
		model.addAttribute("search", new BookSearchDTO("", ""));
		return "main";
	}
	
	@PostMapping("/book")
	private String requestBookPage(@ModelAttribute(name="search") @Valid BookSearchDTO search, 
			BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "main";
		}
		
		String isbn = search.getIsbn();
		
		//Inserte proceso del servicio para filtrar la info
		Book foundBook = library.stream()
			.filter((book)-> book.getIsbn().equals(isbn))
			.findAny()
			.orElse(new Book("", ""));
		
		
		List<String> isbns = library
				.stream()
				.map((book)-> {
					return book.getIsbn();
				})
				.toList();
		
		BookResponseDTO response = 
				new BookResponseDTO(foundBook.getTitle(), search.getPerson(), isbns);
		
		//model.addAttribute("title", foundBook.getTitle());
		//model.addAttribute("person", search.getPerson());
		model.addAttribute("book", response);
		
		return "book";
	}
	
}
