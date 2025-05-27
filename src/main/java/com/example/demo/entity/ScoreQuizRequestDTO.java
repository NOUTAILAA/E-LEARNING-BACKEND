// ✅ ScoreQuizRequestDTO.java
package com.example.demo.entity;

public class ScoreQuizRequestDTO {

    private Long apprenantId;
    private Long quizSectionId;
    private double score;
private Long quizChapitreId;

public Long getQuizChapitreId() {
    return quizChapitreId;
}

public void setQuizChapitreId(Long quizChapitreId) {
    this.quizChapitreId = quizChapitreId;
}

    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
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
}