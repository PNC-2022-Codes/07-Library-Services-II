package com.douglashdezt.library.models.dtos;

public class BookResponseDTO {
	private String title;
	private String person;
	
	public BookResponseDTO(String title, String person) {
		super();
		this.title = title;
		this.person = person;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	public boolean isPersonCorrect() {
		return !this.person.isBlank();
	}
	
	public boolean isTitleCorrect() {
		return !this.title.isBlank();
	}
}
