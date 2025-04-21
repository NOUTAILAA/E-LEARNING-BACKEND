package com.example.demo.repository;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.QuizProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT q.id AS id, q.question AS question, " +
           "CAST(q.dateCreation AS string) AS dateCreation, " +
           "CAST(q.dateMAJ AS string) AS dateMAJ " +
           "FROM Quiz q WHERE q.chapitre.id = :chapitreId")
    List<QuizProjection> findByChapitreIdLight(Long chapitreId);
}