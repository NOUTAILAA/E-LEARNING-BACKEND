package com.example.demo.repository;

import com.example.demo.entity.ReponseQuiz;
import com.example.demo.entity.ReponseQuizId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReponseQuizRepository extends JpaRepository<ReponseQuiz, ReponseQuizId> {
    
    List<ReponseQuiz> findByApprenantId(Long apprenantId);
    
    List<ReponseQuiz> findByQuizId(Long quizId);

    List<ReponseQuiz> findByApprenantIdAndQuizId(Long apprenantId, Long quizId);
}
