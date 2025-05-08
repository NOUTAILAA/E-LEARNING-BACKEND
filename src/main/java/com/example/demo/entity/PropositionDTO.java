package com.example.demo.entity;

public class PropositionDTO {
    private Long id;
    private String reponse;
    private Boolean correcte;

    // Constructeurs
    public PropositionDTO() {}

    public PropositionDTO(Long id, String reponse, Boolean correcte) {
        this.id = id;
        this.reponse = reponse;
        this.correcte = correcte;
    }

    // Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(Boolean correcte) {
        this.correcte = correcte;
    }
    
}
