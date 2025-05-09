package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@IdClass(ReponseQuizSectionId.class)
public class ReponseQuizSection {
@Id
    @ManyToOne
@JoinColumn(name = "apprenant_id")
@JsonIgnore
private Apprenant apprenant;
@Id
@ManyToOne
@JoinColumn(name = "quiz_section_id")
@JsonIgnore
private QuizSection quizSection;
@Id
@ManyToOne
@JoinColumn(name = "proposition_id")
@JsonIgnore
private PropositionSection proposition;

    private boolean correct;

    // Getters & Setters
    public Apprenant getApprenant() { return apprenant; }
    public void setApprenant(Apprenant apprenant) { this.apprenant = apprenant; }

    public QuizSection getQuizSection() { return quizSection; }
    public void setQuizSection(QuizSection quizSection) { this.quizSection = quizSection; }

    public PropositionSection getProposition() { return proposition; }
    public void setProposition(PropositionSection proposition) { this.proposition = proposition; }

    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
}
