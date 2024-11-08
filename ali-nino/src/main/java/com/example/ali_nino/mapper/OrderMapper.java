package com.example.ali_nino.mapper;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ali_nino.dao.entity.Order;
import com.example.ali_nino.model.CourierDto;
import com.example.ali_nino.model.OrderDto;
import com.example.ali_nino.model.UserDto;

@Component
public class OrderMapper {

	public OrderDto entityToDto(Order order) {
		OrderDto orderDto = new OrderDto(); 
		orderDto.setId(order.getId());
		orderDto.setBooks(order.getBooks());
		if(order.getCourier() != null) {
			CourierDto courierDto = new CourierDto();
			courierDto.setId(order.getCourier().getId());
			courierDto.setName(order.getCourier().getName());
			orderDto.setCourier(courierDto);
		}
		orderDto.setStatus(order.getStatus());
		orderDto.setTotalPrice(order.getTotalPrice());
		UserDto userDto = new UserDto();
		userDto.setEmail(order.getUser().getEmail());
		userDto.setName(order.getUser().getName());
		userDto.setUserName(order.getUser().getUserName());
		orderDto.setUser(userDto);
		return orderDto;
	}

	public List<OrderDto> entityListToDtoList(List<Order> orders) {
		List<OrderDto> orderDtos = new ArrayList<>();
		orders.stream().forEach(order -> {
			OrderDto orderDto = entityToDto(order);
			orderDtos.add(orderDto);
		});
		
		return orderDtos;
	}
}
