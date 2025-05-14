package com.example.demo.entity;

public class CompteurRequestDTO {

    private Long apprenantId;
    private Long sectionId;
    private Long quizSectionId;
    private Long quizChapitreId;
    private Double tempsPasse;

    // Getter et Setter pour apprenantId
    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    // Getter et Setter pour sectionId
    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    // Getter et Setter pour quizSectionId
    public Long getQuizSectionId() {
        return quizSectionId;
    }

    public void setQuizSectionId(Long quizSectionId) {
        this.quizSectionId = quizSectionId;
    }

    // Getter et Setter pour quizChapitreId
    public Long getQuizChapitreId() {
        return quizChapitreId;
    }

    public void setQuizChapitreId(Long quizChapitreId) {
        this.quizChapitreId = quizChapitreId;
    }

    // Getter et Setter pour valeur
    public Double getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(Double tempsPasse) {
        this.tempsPasse = tempsPasse;
    }
}
