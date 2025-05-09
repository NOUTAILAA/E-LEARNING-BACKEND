package com.example.demo.entity;

public class QuizSectionDTO {
    private Long id;
    private String question;
    private Long sectionId;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }
public QuizSectionDTO(Long id, String question, Long sectionId) {
    this.id = id;
    this.question = question;
    this.sectionId = sectionId;
}

}
