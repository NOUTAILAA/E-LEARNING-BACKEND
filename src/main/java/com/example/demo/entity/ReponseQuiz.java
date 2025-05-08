package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@IdClass(ReponseQuizId.class)
public class ReponseQuiz {

    @Id
    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;

    @Id
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Id
    @ManyToOne
    @JoinColumn(name = "proposition_id")
    private Proposition proposition;

    private boolean correct;

    // === Getters et Setters ===

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Proposition getProposition() {
        return proposition;
    }

    public void setProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
