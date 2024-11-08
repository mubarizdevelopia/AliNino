package com.example.ali_nino.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ali_nino.dao.entity.Courier;

public interface CourierRepository extends JpaRepository<Courier, Long>{

	Optional<Courier> findById(Long id);
	
}
