package com.example.demo.repository;

import com.example.demo.entity.ScoreQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreQuizRepository extends JpaRepository<ScoreQuiz, Long> {

    List<ScoreQuiz> findByApprenantId(Long apprenantId);

    Optional<ScoreQuiz> findByApprenantIdAndQuizSectionId(Long apprenantId, Long quizSectionId);

    List<ScoreQuiz> findByQuizSectionId(Long quizSectionId);
List<ScoreQuiz> findByQuizChapitreId(Long id);
Optional<ScoreQuiz> findByApprenantIdAndQuizChapitreId(Long apprenantId, Long quizChapitreId);

}
