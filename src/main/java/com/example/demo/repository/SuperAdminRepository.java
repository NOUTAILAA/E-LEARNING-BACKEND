package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SuperAdmin;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByEmailAndPassword(String email, String password);
	Optional<SuperAdmin> findByEmailIgnoreCase(String email);

}
