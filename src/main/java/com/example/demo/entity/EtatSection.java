package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "etat_section", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"apprenant_id", "section_id"})
})
public class EtatSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean etat;  // true = vu/validé, false = non vu

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    @JsonIgnoreProperties({"manager", "departement"})
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties({"chapitres"})
    private Section section;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}