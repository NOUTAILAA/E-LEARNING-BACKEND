package com.example.demo.repository;

import com.example.demo.entity.ReponseQuiz;
import com.example.demo.entity.ReponseQuizId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReponseQuizRepository extends JpaRepository<ReponseQuiz, ReponseQuizId> {
    
    List<ReponseQuiz> findByApprenantId(Long apprenantId);
    
    List<ReponseQuiz> findByQuizId(Long quizId);

    List<ReponseQuiz> findByApprenantIdAndQuizId(Long apprenantId, Long quizId);
@Query("SELECT r.correct FROM ReponseQuiz r " +
       "WHERE r.apprenant.id = :apprenantId AND r.quiz.id = :quizId")
List<Boolean> findCorrectFlagsByApprenantAndQuiz(@Param("apprenantId") Long apprenantId,
                                                  @Param("quizId") Long quizId);

}
