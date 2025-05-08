package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Departement;
import com.example.demo.repository.ApprenantRepository;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.util.PasswordGenerator;

@Service
public class ApprenantService {
    @Autowired
    private ApprenantRepository apprenantRepository;
    @Autowired
    private DepartementRepository departementRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // Utilisation de BCryptPasswordEncoder
    // Créer un nouvel apprenant avec un mot de passe haché
    @Autowired
    private EmailService emailService;
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

        // 1. Générer un mot de passe aléatoire
        String plainPassword = PasswordGenerator.generateRandomPassword();

        // 2. Hasher le mot de passe
        String hashedPassword = passwordEncoder.encode(plainPassword);
        apprenant.setPassword(hashedPassword);

        // 3. Sauvegarder l’apprenant
        Apprenant savedApprenant = apprenantRepository.save(apprenant);

        // 4. Envoyer l’email avec le mot de passe en clair
        emailService.sendActivationEmail(savedApprenant.getEmail(), savedApprenant.getNom(), plainPassword);

        return savedApprenant;
    }


        // Mettre à jour un apprenant avec un mot de passe haché
        public Apprenant update(Apprenant apprenantDetails) {
            Apprenant apprenant = apprenantRepository.findById(apprenantDetails.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Apprenant non trouvé"));

            apprenant.setNom(apprenantDetails.getNom());
            apprenant.setPrenom(apprenantDetails.getPrenom());
            apprenant.setDateNaissance(apprenantDetails.getDateNaissance());
            apprenant.setTelephone(apprenantDetails.getTelephone());
            apprenant.setSexe(apprenantDetails.getSexe());
            apprenant.setEmail(apprenantDetails.getEmail());

            // Si un mot de passe est fourni, le hacher et le mettre à jour
            if (apprenantDetails.getPassword() != null) {
                String hashedPassword = passwordEncoder.encode(apprenantDetails.getPassword());
                apprenant.setPassword(hashedPassword);
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
    public Apprenant getApprenantById(Long id) {
        Optional<Apprenant> optional = apprenantRepository.findById(id);
        return optional.orElse(null);
    }
}