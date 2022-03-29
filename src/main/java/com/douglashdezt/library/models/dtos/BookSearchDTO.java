package com.douglashdezt.library.models.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookSearchDTO {
	
	@NotBlank(message = "¡ISBN Vacío!")
	@Size(min=10, max=10)
	private String isbn;
	
	@NotBlank(message = "¡Persona Vacía!")
	@Size(min=2, max=10)
	private String person;
	
	public BookSearchDTO(String isbn, String person) {
		super();
		this.isbn = isbn;
		this.person = person;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
}
