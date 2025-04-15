package com.example.demo.repository;

import com.example.demo.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    List<Cours> findByProjetId(Long projetId);
}
