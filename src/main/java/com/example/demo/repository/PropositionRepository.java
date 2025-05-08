package com.example.demo.repository;

import com.example.demo.entity.Proposition;
import com.example.demo.entity.PropositionProjection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropositionRepository extends JpaRepository<Proposition, Long> {

    @Query("SELECT p.id AS id, p.reponse AS reponse, p.correcte AS correcte " +
           "FROM Proposition p WHERE p.quiz.id = :quizId")
    List<PropositionProjection> findLightByQuizId(@Param("quizId") Long quizId);
}
