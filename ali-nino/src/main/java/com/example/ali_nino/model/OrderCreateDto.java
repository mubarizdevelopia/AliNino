package com.example.ali_nino.model;

import java.util.List;

public class OrderCreateDto {
	private List<Long> bookIds;

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}
	
}
