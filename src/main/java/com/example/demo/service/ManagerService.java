package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;


@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UtilisateurService utilisateurService; // Injecter UtilisateurService

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Manager save(Manager manager) {
        // Si le rôle est null ou vide, on le définit par défaut à "manager"
        if (manager.getRole() == null || manager.getRole().isEmpty()) {
            manager.setRole("manager");
        }
        return managerRepository.save(manager);
    }
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    public void delete(Long id) {
        managerRepository.deleteById(id);
    }
    // Utiliser UtilisateurService pour afficher un message de bienvenue
    public String greetUser(Manager manager) {
        return utilisateurService.greetUser(manager);
    }
    // Méthode pour trouver un manager par email et mot de passe
    public Optional<Manager> findByEmailAndPassword(String email, String password) {
        return managerRepository.findByEmailAndPassword(email, password);  // Méthode à ajouter dans le repository
    }
}

