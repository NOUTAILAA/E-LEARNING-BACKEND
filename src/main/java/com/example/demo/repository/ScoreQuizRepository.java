package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ScoreQuiz;


import java.util.List;
import java.util.Optional;

public interface ScoreQuizRepository extends JpaRepository<ScoreQuiz, Long> {

    List<ScoreQuiz> findByApprenantId(Long apprenantId);

    List<ScoreQuiz> findByQuizId(Long quizId);

    Optional<ScoreQuiz> findByApprenantIdAndQuizId(Long apprenantId, Long quizId);
}
