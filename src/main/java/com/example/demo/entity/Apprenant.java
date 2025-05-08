package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Apprenant extends Utilisateur {
    // La clé étrangère qui lie un Apprenant à un Manager
    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Manager manager;
    
    @ManyToOne
    @JoinColumn(name = "departement_id")  // La clé étrangère qui lie un Apprenant à un Departement
    private Departement departement;
    @OneToMany(mappedBy = "apprenant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ReponseQuiz> reponsesQuiz;
    
    // Getter et setter
    public List<ReponseQuiz> getReponsesQuiz() {
        return reponsesQuiz;
    }
    
    public void setReponsesQuiz(List<ReponseQuiz> reponsesQuiz) {
        this.reponsesQuiz = reponsesQuiz;
    }
    
    // Getters et Setters
    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
    public Manager getManager() {
        return manager;
    }
    
    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
