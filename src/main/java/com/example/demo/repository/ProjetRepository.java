package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    List<Projet> findByManagerId(Long managerId);
    
}
