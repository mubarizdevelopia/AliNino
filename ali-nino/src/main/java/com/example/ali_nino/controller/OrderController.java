package com.example.ali_nino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ali_nino.dao.entity.Order;
import com.example.ali_nino.model.OrderCreateDto;
import com.example.ali_nino.model.OrderDto;
import com.example.ali_nino.service.OrderService;

@RestController
@RequestMapping("/v1/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<OrderDto> getAllUserOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/details/{orderId}")
	public OrderDto getUserOrderDetails(@PathVariable("orderId") Long orderId) {
		return orderService.getUserOrderDetails(orderId);
	}

	@PostMapping
	public OrderDto createOrder(@RequestBody OrderCreateDto orderDto) {
		return orderService.createOrder(orderDto);
	}

	@PutMapping("/{orderId}/courier/{id}")
	public OrderDto assingOrderToCourier(@PathVariable("orderId") Long orderId, @PathVariable("id") Long courierId) {
		return orderService.assingOrderToCourier(orderId, courierId);
	}

}
