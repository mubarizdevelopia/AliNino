package com.example.ali_nino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ali_nino.dao.entity.Book;
import com.example.ali_nino.dao.entity.Courier;
import com.example.ali_nino.dao.entity.Order;
import com.example.ali_nino.dao.entity.User;
import com.example.ali_nino.dao.repository.BookRepository;
import com.example.ali_nino.dao.repository.CourierRepository;
import com.example.ali_nino.dao.repository.OrderRepository;
import com.example.ali_nino.exception.NotFoundException;
import com.example.ali_nino.helper.UserHelper;
import com.example.ali_nino.mapper.OrderMapper;
import com.example.ali_nino.model.OrderCreateDto;
import com.example.ali_nino.model.OrderDto;
import com.example.ali_nino.security.JwtUtils;

@Service
public class OrderService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired 
	private JwtUtils jwtUtils;

	public List<OrderDto> getAllOrders() {
		String userName = jwtUtils.getAuthenticatedUserId();
		User user = userHelper.findUserByUserName(userName);
		List<Order> orders = orderRepository.findByUserId(user.getId());
		List<OrderDto> orderDtos = orderMapper.entityListToDtoList(orders);
		return orderDtos;
	}

	public OrderDto getUserOrderDetails(Long orderId) {
		String userName = jwtUtils.getAuthenticatedUserId();
		User user = userHelper.findUserByUserName(userName);
		Order order = orderRepository.findByUserIdAndId(user.getId(), orderId);
		if (order == null) {
			throw new NotFoundException("Qeyd olunan id-li order yoxdur");
		}
		OrderDto orderDto = orderMapper.entityToDto(order);
		return orderDto;
	}

	public OrderDto createOrder(OrderCreateDto orderCreateDto) {
		String userName = jwtUtils.getAuthenticatedUserId();
		User user = userHelper.findUserByUserName(userName);
		List<Book> books = bookRepository.findByIdIn(orderCreateDto.getBookIds());
		Order order = new Order();
		order.setUser(user);
		order.setBooks(books);
		order.setStatus("ORDERED");
		Double totalPrice = calculateTotalPrice(books);
		order.setTotalPrice(totalPrice);
		orderRepository.save(order);
		OrderDto orderDto = orderMapper.entityToDto(order);
		return orderDto;
	}

	public OrderDto assingOrderToCourier(Long orderId, Long courierId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> {
			throw new NotFoundException("Order not found with given id");
		});
		
		Courier courier = courierRepository.findById(courierId).orElseThrow(() -> {
			throw new NotFoundException("Courier not found with given id");
		});
		
		order.setCourier(courier);
		order.setStatus("DELIVERY");
		orderRepository.save(order);
		return orderMapper.entityToDto(order);
	}

	private Double calculateTotalPrice(List<Book> books) {
		Double totalPrice = books.stream().mapToDouble(it -> it.getPrice()).sum();
		return totalPrice;
	}

}
