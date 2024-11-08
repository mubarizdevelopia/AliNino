package com.example.ali_nino.model;

import java.util.List;

import com.example.ali_nino.dao.entity.Book;
import com.example.ali_nino.dao.entity.Courier;
import com.example.ali_nino.dao.entity.User;

public class OrderDto {
	private Long id;
	private Double totalPrice;
	private String status;
	private UserDto user;
	private CourierDto courier;
	private List<Book> books;
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public CourierDto getCourier() {
		return courier;
	}
	public void setCourier(CourierDto courier) {
		this.courier = courier;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
