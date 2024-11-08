package com.example.ali_nino.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ali_nino.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findById(Long id);
	
	Optional<User> findByUserName(String username);
    boolean existsByUserName(String username);
}
