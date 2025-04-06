package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Departement;
import com.example.demo.entity.Manager;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.ManagerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.demo.util.PasswordGenerator;


@Service
public class ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private EmailService emailService; // Injection du service email

    @Autowired
    private UtilisateurService utilisateurService; // Injecter UtilisateurService

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

 
    public Manager save(Manager manager) {
        // Vérifier s’il existe un autre utilisateur avec cet email
        Optional<Manager> existingByEmail = managerRepository.findByEmail(manager.getEmail());
        if (existingByEmail.isPresent() && !existingByEmail.get().getId().equals(manager.getId())) {
            throw new IllegalArgumentException("Un utilisateur avec cet email existe déjà.");
        }

        // Vérifier s’il existe un autre utilisateur avec ce téléphone
        Optional<Manager> existingByPhone = managerRepository.findByTelephone(manager.getTelephone());
        if (existingByPhone.isPresent() && !existingByPhone.get().getId().equals(manager.getId())) {
            throw new IllegalArgumentException("Un utilisateur avec ce téléphone existe déjà.");
        }

        if (manager.getRole() == null || manager.getRole().isEmpty()) {
            manager.setRole("manager");
        }

        if (manager.getDepartement() != null) {
            Departement departement = departementRepository.findById(manager.getDepartement().getId())
                .orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
            manager.setDepartement(departement);
        }

        // Ne regénère pas le mot de passe si le manager a déjà un ID (update)
        if (manager.getId() == null) {
            String plainPassword = PasswordGenerator.generateRandomPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(plainPassword);
            manager.setPassword(hashedPassword);
            Manager savedManager = managerRepository.save(manager);
            emailService.sendActivationEmail(savedManager.getEmail(), savedManager.getNom(), plainPassword);
            return savedManager;
        }

        return managerRepository.save(manager);  // Mise à jour normale
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

