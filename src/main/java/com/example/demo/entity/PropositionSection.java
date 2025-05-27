package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class PropositionSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reponse;

    private boolean correcte; // ✅ Manquait !

@ManyToOne
@JoinColumn(name = "quiz_section_id")
@JsonBackReference
private QuizSection quiz;

    // === Getters & Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(boolean correcte) {
        this.correcte = correcte;
    }

    public QuizSection getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizSection quiz) {
        this.quiz = quiz;
    }
}
