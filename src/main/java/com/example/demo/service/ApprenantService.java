package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Departement;
import com.example.demo.repository.ApprenantRepository;
import com.example.demo.repository.DepartementRepository;

@Service
public class ApprenantService {
    @Autowired
    private ApprenantRepository apprenantRepository;
    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private UtilisateurService utilisateurService; // Injecter UtilisateurService

    public List<Apprenant> findAll() {
        return apprenantRepository.findAll();
    }

    public Apprenant save(Apprenant apprenant) {
        if (apprenant.getRole() == null || apprenant.getRole().isEmpty()) {
            apprenant.setRole("apprenant");
        }
        if (apprenant.getDepartement() != null) {
            Departement departement = departementRepository.findById(apprenant.getDepartement().getId())
                .orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
            apprenant.setDepartement(departement);
        }
        return apprenantRepository.save(apprenant);
    }


    public Optional<Apprenant> findById(Long id) {
        return apprenantRepository.findById(id);
    }

    public void delete(Long id) {
        apprenantRepository.deleteById(id);
    }
    // Utiliser UtilisateurService pour afficher un message de bienvenue
    public String greetUser(Apprenant apprenant) {
        return utilisateurService.greetUser(apprenant);
    }
    // Méthode pour trouver un apprenant par email et mot de passe
    public Optional<Apprenant> findByEmailAndPassword(String email, String password) {
        return apprenantRepository.findByEmailAndPassword(email, password);  // Méthode à ajouter dans le repository
    }
}