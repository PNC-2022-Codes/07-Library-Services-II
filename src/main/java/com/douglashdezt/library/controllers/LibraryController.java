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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglashdezt.library.models.dtos.BookAddDTO;
import com.douglashdezt.library.models.dtos.BookDetailsDTO;
import com.douglashdezt.library.models.dtos.BookResponseDTO;
import com.douglashdezt.library.models.dtos.BookSearchDTO;
import com.douglashdezt.library.models.entities.Book;
import com.douglashdezt.library.models.entities.User;
import com.douglashdezt.library.services.BookService;
import com.douglashdezt.library.services.UserService;
import com.douglashdezt.library.services.utils.ServiceResponse;

@Controller
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
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
		
		String username = search.getPerson();
		User foundUser = userService.getOneById(username);
		
		if(foundUser == null) {
			model.addAttribute("error", "Usuario no econtrado");
			return "main";
		}
		
		String isbn = search.getIsbn();
		ServiceResponse<Book> foundBookResponse 
			= bookService.getOneById(isbn);
		
		if(!foundBookResponse.getStatus()) {
			model.addAttribute("error", "Libro no encontrado!");
			return "main";
		}
		
		Book foundBook = foundBookResponse.getData();
		
		List<String> isbns = bookService
				.getAllIsbns()
				.getData();
		
		BookResponseDTO response = 
				new BookResponseDTO(
						foundBook.getTitle(), 
						foundUser.getFirstname() + " " + foundUser.getLastname(), 
						isbns
					);
		
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
		
		ServiceResponse<Book> foundBookResponse 
			= bookService.getOneById(bookInfo.getIsbn());
		
		if(foundBookResponse.getStatus()) {
			model.addAttribute("error", "Este libro ya existe");
			return "add_book";
		}
		
		ServiceResponse<Void> insertResponse 
			= bookService.insert(new Book(bookInfo.getIsbn(), bookInfo.getTitle()));
		
		if(!insertResponse.getStatus()) {
			model.addAttribute("error", "El guardado ha fallado");
			return "add_book";
		}
		
		return "redirect:/library/";
	}
	
	@GetMapping("/book/details/{id}")
	public String getBookDetailsPage(
			@PathVariable(name="id") String isbn, 
			Model model) 
	{
		
		ServiceResponse<Book> foundBookResponse 
			= bookService.getOneById(isbn);
		
		if(!foundBookResponse.getStatus()) {
			//TODO: IMplmentar página 404
			return "redirect:/library/";
		}
		
		Book foundBook = foundBookResponse.getData();
		
		BookDetailsDTO reponse = new BookDetailsDTO(foundBook.getTitle());
		model.addAttribute("book", reponse);
		
		return "book_details";
	}
}
