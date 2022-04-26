package com.douglashdezt.library.models.dtos;

public class BookDetailsDTO {
	private String title;

	public BookDetailsDTO(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
