package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Manager extends Utilisateur {
		@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	private List<Apprenant> apprenants = new ArrayList<>();

	public List<Apprenant> getApprenants() {
		return apprenants;
	}

	public void setApprenants(List<Apprenant> apprenants) {
		this.apprenants = apprenants;
	}

	@ManyToOne
	    @JoinColumn(name = "departement_id")  // La clé étrangère qui lie un Apprenant à un Departement
	    private Departement departement;
		@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
		@JsonManagedReference
		@JsonIgnore // ⚠️ évite la boucle infinie et la sérialisation du LOB
		private List<Projet> projets = new ArrayList<>();
		
		public List<Projet> getProjets() {
			return projets;
		}
		
		public void setProjets(List<Projet> projets) {
			this.projets = projets;
		}
		
	    // Getters et Setters
	    public Departement getDepartement() {
	        return departement;
	    }

	    public void setDepartement(Departement departement) {
	        this.departement = departement;
	    }
}
