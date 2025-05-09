package com.example.demo.repository;

import com.example.demo.entity.QuizSection;
import com.example.demo.entity.QuizSectionDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizSectionRepository extends JpaRepository<QuizSection, Long> {
    List<QuizSection> findBySectionId(Long sectionId);
@Query("SELECT new com.example.demo.entity.QuizSectionDTO(q.id, q.question, q.section.id) FROM QuizSection q WHERE q.section.id = :sectionId")
List<QuizSectionDTO> findQuizDTOBySectionId(@Param("sectionId") Long sectionId);

}
