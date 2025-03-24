package com.example.demo.service;


import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Manager;
import com.example.demo.entity.SuperAdmin;
import com.example.demo.entity.Utilisateur;

@Service
public class UtilisateurService {

    // Méthode pour définir le rôle par défaut
    public void setDefaultRole(Utilisateur utilisateur) {
        if (utilisateur.getRole() == null || utilisateur.getRole().isEmpty()) {
            // Assigner un rôle par défaut en fonction de la classe de l'utilisateur
            if (utilisateur instanceof Admin) {
                utilisateur.setRole("admin");
            } else if (utilisateur instanceof Manager) {
                utilisateur.setRole("manager");
            } else if (utilisateur instanceof Apprenant) {
                utilisateur.setRole("apprenant");
            } else if (utilisateur instanceof SuperAdmin) {
                utilisateur.setRole("superadmin");
            }
        }
    }

    // Méthode pour afficher un message de bienvenue
    public String greetUser(Utilisateur utilisateur) {
        return "Bonjour " + utilisateur.getNom() + " avec le rôle " + utilisateur.getRole();
    }
}
