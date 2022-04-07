package com.douglashdezt.library.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglashdezt.library.models.dtos.BookAddDTO;
import com.douglashdezt.library.models.dtos.BookResponseDTO;
import com.douglashdezt.library.models.dtos.BookSearchDTO;
import com.douglashdezt.library.models.entities.Book;
import com.douglashdezt.library.services.BookService;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	private BookService bookService;
	
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
		Book foundBook = bookService.getOneById(isbn);
		
		if(foundBook == null) {
			model.addAttribute("error", "Libro no encontrado!");
			return "main";
		}
		
		List<String> isbns = bookService.getAllIsbns();
		
		BookResponseDTO response = 
				new BookResponseDTO(foundBook.getTitle(), search.getPerson(), isbns);
		
		//model.addAttribute("title", foundBook.getTitle());
		//model.addAttribute("person", search.getPerson());
		model.addAttribute("book", response);
		
		return "book";
	}
	
	@GetMapping("/book/add")
	public String getAddPage(Model model) {
		model.addAttribute("book", new BookAddDTO("", ""));
		return "add_book";
	}
	
	@PostMapping("/book/add")
	public String addBook(
		@ModelAttribute(name="book") @Valid BookAddDTO bookInfo,
		BindingResult result,
		Model model
	) {
		if(result.hasErrors()) {
			return "add_book";
		}
		
		Book foundBook = bookService.getOneById(bookInfo.getIsbn());
		
		if(foundBook != null) {
			model.addAttribute("error", "Este libro ya existe");
			return "add_book";
		}
		
		bookService.insert(new Book(bookInfo.getIsbn(), bookInfo.getTitle()));
		return "redirect:/library/";
	}
	
}
