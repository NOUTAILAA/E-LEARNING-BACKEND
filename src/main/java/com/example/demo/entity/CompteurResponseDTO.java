package com.example.demo.entity;

public class CompteurResponseDTO {
    private Long id;
    private Long apprenantId;
    private Long sectionId;
    private Long quizSectionId;
    private Long quizChapitreId;
    private Double tempsPasse;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApprenantId() {
        return apprenantId;
    }

    public void setApprenantId(Long apprenantId) {
        this.apprenantId = apprenantId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getQuizSectionId() {
        return quizSectionId;
    }

    public void setQuizSectionId(Long quizSectionId) {
        this.quizSectionId = quizSectionId;
    }

    public Long getQuizChapitreId() {
        return quizChapitreId;
    }

    public void setQuizChapitreId(Long quizChapitreId) {
        this.quizChapitreId = quizChapitreId;
    }

    public Double getTempsPasse() {
        return tempsPasse;
    }

    public void setTempsPasse(Double tempsPasse) {
        this.tempsPasse = tempsPasse;
    }
}
