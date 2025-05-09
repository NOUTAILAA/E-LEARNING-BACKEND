package com.example.demo.repository;

import com.example.demo.entity.ReponseQuizSection;
import com.example.demo.entity.ReponseQuizSectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReponseQuizSectionRepository extends JpaRepository<ReponseQuizSection, ReponseQuizSectionId> {

    List<ReponseQuizSection> findByApprenantId(Long apprenantId);
    List<ReponseQuizSection> findByQuizSectionId(Long quizId);
    List<ReponseQuizSection> findByApprenantIdAndQuizSectionId(Long apprenantId, Long quizSectionId);

    @Query("SELECT r.correct FROM ReponseQuizSection r WHERE r.apprenant.id = :apprenantId AND r.quizSection.id = :quizSectionId")
    List<Boolean> findCorrectFlagsByApprenantAndQuiz(Long apprenantId, Long quizSectionId);
}
