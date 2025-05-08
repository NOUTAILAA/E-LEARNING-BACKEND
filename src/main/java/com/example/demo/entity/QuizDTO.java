package com.example.demo.entity;

import java.util.List;

public class QuizDTO {
    private Long id;
    private String question;
    private List<PropositionDTO> propositions;

    public QuizDTO() {}

    public QuizDTO(Long id, String question, List<PropositionDTO> propositions) {
        this.id = id;
        this.question = question;
        this.propositions = propositions;
    }

    // === Getters & Setters ===
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<PropositionDTO> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<PropositionDTO> propositions) {
        this.propositions = propositions;
    }
}
