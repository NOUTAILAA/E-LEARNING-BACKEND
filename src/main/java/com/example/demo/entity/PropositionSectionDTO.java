package com.example.demo.entity;

public class PropositionSectionDTO {
    private Long id;
    private String reponse;
    private boolean correcte;
    private Long quizId;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }

    public boolean isCorrecte() { return correcte; }
    public void setCorrecte(boolean correcte) { this.correcte = correcte; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }
public PropositionSectionDTO(Long id, String reponse, boolean correcte, Long quizId) {
    this.id = id;
    this.reponse = reponse;
    this.correcte = correcte;
    this.quizId = quizId;
}
public PropositionSectionDTO(){}
}
