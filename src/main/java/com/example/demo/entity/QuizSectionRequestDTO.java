package com.example.demo.entity;

import java.util.List;

public class QuizSectionRequestDTO {
    private String question;
    private Long sectionId;
    private List<PropositionDTO> propositions;

    public static class PropositionDTO {
        private String reponse;
        private boolean correcte;

        // Getters et setters
        public String getReponse() { return reponse; }
        public void setReponse(String reponse) { this.reponse = reponse; }

        public boolean isCorrecte() { return correcte; }
        public void setCorrecte(boolean correcte) { this.correcte = correcte; }
    }

    // Getters et setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Long getSectionId() { return sectionId; }
    public void setSectionId(Long sectionId) { this.sectionId = sectionId; }

    public List<PropositionDTO> getPropositions() { return propositions; }
    public void setPropositions(List<PropositionDTO> propositions) { this.propositions = propositions; }
}
