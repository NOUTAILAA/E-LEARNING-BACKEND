package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByEmailAndPassword(String email, String password);
    Optional<Manager> findByEmailIgnoreCase(String email);
    Optional<Manager> findByTelephone(String telephone);


}
