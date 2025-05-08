package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Temporal(TemporalType.DATE)
    private Date dateMAJ;

    @ManyToOne
    @JoinColumn(name = "chapitre_id")
    @JsonIgnore
    private Chapitre chapitre;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposition> propositions = new ArrayList<>();

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }

    // ====== Getters & Setters ======
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMAJ() {
        return dateMAJ;
    }

    public void setDateMAJ(Date dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public Chapitre getChapitre() {
        return chapitre;
    }

    public void setChapitre(Chapitre chapitre) {
        this.chapitre = chapitre;
    }
}
