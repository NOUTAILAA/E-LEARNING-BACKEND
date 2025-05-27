package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class ScoreQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Apprenant apprenant;
@ManyToOne
private Quiz quizChapitre;
    @ManyToOne
    private QuizSection quizSection;

    private Double score;

    // Getters et Setters
    public Quiz getQuizChapitre() {
    return quizChapitre;
}

public void setQuizChapitre(Quiz quizChapitre) {
    this.quizChapitre = quizChapitre;
}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public QuizSection getQuizSection() {
        return quizSection;
    }

    public void setQuizSection(QuizSection quizSection) {
        this.quizSection = quizSection;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}