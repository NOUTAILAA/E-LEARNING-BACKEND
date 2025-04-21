package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    @OneToMany(mappedBy = "departement")
    @JsonBackReference(value = "departement-projets")
    private List<Projet> projets;

    @OneToMany(mappedBy = "departement")  // mappedBy indique que l'association est gérée par la propriété departement dans Apprenant
    @JsonIgnore  // Ignorer la sérialisation de la relation apprenants pour éviter la boucle infinie
    private List<Apprenant> apprenants;
    @OneToMany(mappedBy = "departement")  // mappedBy indique que l'association est gérée par la propriété departement dans Manager
    @JsonIgnore  // Ignorer la sérialisation de la relation apprenants pour éviter la boucle infinie
    private List<Manager> managers;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Apprenant> getApprenants() {
        return apprenants;
    }

    public void setApprenants(List<Apprenant> apprenants) {
        this.apprenants = apprenants;
    }

	public List<Manager> getManagers() {
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}
    
}
