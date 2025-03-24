package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UtilisateurService utilisateurService; // Injecter UtilisateurService

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin save(Admin admin) {
        // Si le rôle est null ou vide, on le définit par défaut à "admin"
        if (admin.getRole() == null || admin.getRole().isEmpty()) {
            admin.setRole("admin");
        }
        return adminRepository.save(admin);
    }
    
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    // Utiliser UtilisateurService pour afficher un message de bienvenue
    public String greetUser(Admin admin) {
        return utilisateurService.greetUser(admin);
    }
    // Méthode pour trouver un admin par email et mot de passe
    public Optional<Admin> findByEmailAndPassword(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);  // Méthode à ajouter dans le repository
    }
}



