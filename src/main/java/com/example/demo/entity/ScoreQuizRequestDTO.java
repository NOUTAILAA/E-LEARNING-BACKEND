package com.example.demo.entity;

public class ScoreQuizRequestDTO {

    private Long apprenantId;
    private Long quizSectionId;
    private double score;

    // Getters et Setters

    public Long getApprenant() {
        return apprenantId;
    }

    public void setApprenant(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    public Long getQuizSectionId() {
        return quizSectionId;
    }

    public void setQuizSectionId(Long quizSectionId) {
        this.quizSectionId = quizSectionId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
public Long getQuizId() { return quizSectionId; }
}
