package com.example.demo.repository;

import com.example.demo.entity.PropositionSection;
import com.example.demo.entity.PropositionSectionDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropositionSectionRepository extends JpaRepository<PropositionSection, Long> {
    List<PropositionSection> findByQuizId(Long quizId);

@Query("SELECT new com.example.demo.entity.PropositionSectionDTO(p.id, p.reponse, p.correcte, p.quiz.id) " +
       "FROM PropositionSection p WHERE p.quiz.id = :quizId")
List<PropositionSectionDTO> findDTOByQuizId(@Param("quizId") Long quizId);

}
