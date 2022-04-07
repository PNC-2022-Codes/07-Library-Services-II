package com.douglashdezt.library.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookAddDTO {
	@NotBlank
	@Size(min=10, max=10)
	private String isbn;
	
	@NotBlank
	@Size(min=2)
	private String title;
	
	public BookAddDTO(String isbn, String title) {
		super();
		this.isbn = isbn;
		this.title = title;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
