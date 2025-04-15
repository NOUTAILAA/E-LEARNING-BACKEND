package com.example.demo.repository;

import com.example.demo.entity.Chapitre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
    List<Chapitre> findByCoursId(Long coursId);
}
