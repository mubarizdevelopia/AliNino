package com.example.ali_nino.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ali_nino.dao.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	List<Order> findByUserId(Long userId);
	
	Order findByUserIdAndId(Long userId,Long id);
	Optional<Order> findById(Long id); 
}
