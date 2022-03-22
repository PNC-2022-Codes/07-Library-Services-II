package com.douglashdezt.library.models.dtos;

public class BookSearchDTO {
	private String isbn;
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
