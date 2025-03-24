package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Apprenant extends Utilisateur {

    @ManyToOne
    @JoinColumn(name = "departement_id")  // La clé étrangère qui lie un Apprenant à un Departement
    private Departement departement;

    // Getters et Setters
    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
