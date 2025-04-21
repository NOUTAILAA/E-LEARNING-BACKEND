package com.example.demo.entity;

public class QuizRequestDTO {
    private String question;
    private Long chapitreId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getChapitreId() {
        return chapitreId;
    }

    public void setChapitreId(Long chapitreId) {
        this.chapitreId = chapitreId;
    }
public QuizRequestDTO(){}
}