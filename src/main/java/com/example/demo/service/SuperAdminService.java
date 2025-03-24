package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SuperAdmin;
import com.example.demo.repository.SuperAdminRepository;

@Service
public class SuperAdminService {
    @Autowired
    private SuperAdminRepository superAdminRepository;

    @Autowired
    private UtilisateurService utilisateurService; // Injecter UtilisateurService

    public List<SuperAdmin> findAll() {
        return superAdminRepository.findAll();
    }

    public SuperAdmin save(SuperAdmin superAdmin) {
        // Si le rôle est null ou vide, on le définit par défaut à "superadmin"
        if (superAdmin.getRole() == null || superAdmin.getRole().isEmpty()) {
            superAdmin.setRole("superadmin");
        }
        return superAdminRepository.save(superAdmin);
    }

    public Optional<SuperAdmin> findById(Long id) {
        return superAdminRepository.findById(id);
    }

    public void delete(Long id) {
        superAdminRepository.deleteById(id);
    }
    // Utiliser UtilisateurService pour afficher un message de bienvenue
    public String greetUser(SuperAdmin superAdmin) {
        return utilisateurService.greetUser(superAdmin);
    }
    // Méthode pour trouver un superadmin par email et mot de passe
    public Optional<SuperAdmin> findByEmailAndPassword(String email, String password) {
        return superAdminRepository.findByEmailAndPassword(email, password);  // Méthode à ajouter dans le repository
    }
}

