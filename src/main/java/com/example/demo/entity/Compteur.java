package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Compteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Apprenant apprenant;

    @ManyToOne
    private Section section;

    @ManyToOne
    private QuizSection quizSection;

    @ManyToOne
    private Quiz quizChapitre;

    private Double tempsPasse; // en minutes ou secondes

    private Double score; // Pour les quiz, entre 0 et 100 ou 0 et 1

    // Getters et Setters
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public QuizSection getQuizSection() {
        return quizSection;
    }

    public void setQuizSection(QuizSection quizSection) {
        this.quizSection = quizSection;
    }

    public Quiz getQuizChapitre() {
        return quizChapitre;
    }

    public void setQuizChapitre(Quiz quizChapitre) {
        this.quizChapitre = quizChapitre;
    }

    public Double getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(Double tempsPasse) {
        this.tempsPasse = tempsPasse;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
