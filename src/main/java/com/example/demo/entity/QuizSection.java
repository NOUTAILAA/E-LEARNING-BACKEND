package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class QuizSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnore

    private Section section;

@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
@JsonManagedReference
private List<PropositionSection> propositions;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Section getSection() { return section; }
    public void setSection(Section section) { this.section = section; }

    public List<PropositionSection> getPropositions() { return propositions; }
    public void setPropositions(List<PropositionSection> propositions) { this.propositions = propositions; }
}
