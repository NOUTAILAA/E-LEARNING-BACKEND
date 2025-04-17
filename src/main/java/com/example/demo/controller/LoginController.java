package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.Manager;
import com.example.demo.entity.SuperAdmin;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.ApprenantService;
import com.example.demo.service.ManagerService;
import com.example.demo.service.SuperAdminService;
import com.example.demo.repository.ApprenantRepository;
    @RestController
    @RequestMapping("/api/")
    public class LoginController {

        @Autowired
        private AdminService adminService;
        
        @Autowired
        private ApprenantService apprenantService;
        
        @Autowired
        private ManagerService managerService;
        @Autowired
        private ManagerRepository managerRepository;
        @Autowired
        private ApprenantRepository apprenantRepository;
        @Autowired
        private SuperAdminService superAdminService;
        @Autowired
        private PasswordEncoder passwordEncoder;
        
        @PostMapping("login")
        public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            // Vérification pour Manager
            Optional<Apprenant> apprenantOpt = apprenantRepository.findByEmail(email);
                if (apprenantOpt.isPresent()) {
                    Apprenant apprenant = apprenantOpt.get();
                    if (passwordEncoder.matches(password, apprenant.getPassword())) {
                        return ResponseEntity.ok("Bonjour " + apprenant.getNom() + ", vous êtes un " + apprenant.getRole());
                    }
                }
            Optional<Manager> managerOpt = managerRepository.findByEmail(email);
                if (managerOpt.isPresent()) {
                    Manager manager = managerOpt.get();
                    if (passwordEncoder.matches(password, manager.getPassword())) {
                        return ResponseEntity.ok("Bonjour " + manager.getNom() + ", vous êtes un " + manager.getRole());
                    }
                }
    
                
            // Vérification pour Admin
            Optional<Admin> admin = adminService.findByEmailAndPassword(email, password);
            if (admin.isPresent()) {
                return ResponseEntity.ok("Bonjour " + admin.get().getNom() + ", vous êtes un " + admin.get().getRole());
            }

            // Vérification pour Apprenant
            Optional<Apprenant> apprenant = apprenantService.findByEmailAndPassword(email, password);
            if (apprenant.isPresent()) {
                String departementName = apprenant.get().getDepartement() != null ? apprenant.get().getDepartement().getNom() : "Aucun département attribué";
                return ResponseEntity.ok("Bonjour " + apprenant.get().getNom() + ", vous êtes un " + apprenant.get().getRole() + " dans le département : " + departementName);
            }

            

            // Vérification pour SuperAdmin
            Optional<SuperAdmin> superAdmin = superAdminService.findByEmailAndPassword(email, password);
            if (superAdmin.isPresent()) {
                return ResponseEntity.ok("Bonjour " + superAdmin.get().getNom() + ", vous êtes un " + superAdmin.get().getRole());
            }

            // Si aucun utilisateur trouvé
            return ResponseEntity.status(401).body("Identifiants incorrects.");
        }
        
        // Méthode d'inscription
        @PostMapping("register")
        public String register(@RequestBody Utilisateur utilisateur) {
            
            // Créez un utilisateur spécifique en fonction du rôle
            if (utilisateur.getRole().equals("admin")) {
                Admin admin = new Admin();
                admin.setNom(utilisateur.getNom());
                admin.setPrenom(utilisateur.getPrenom());
                admin.setDateNaissance(utilisateur.getDateNaissance());
                admin.setTelephone(utilisateur.getTelephone());
                admin.setSexe(utilisateur.getSexe());
                admin.setEmail(utilisateur.getEmail());
                admin.setPassword(utilisateur.getPassword());
                admin.setRole("admin"); // Assurez-vous que le rôle est bien attribué
                admin.setEtat(utilisateur.getEtat());
                adminService.save(admin); // Sauvegarder un Admin
                
            } else if (utilisateur.getRole().equals("apprenant")) {
                Apprenant apprenant = new Apprenant();
                apprenant.setNom(utilisateur.getNom());
                apprenant.setPrenom(utilisateur.getPrenom());
                apprenant.setDateNaissance(utilisateur.getDateNaissance());
                apprenant.setTelephone(utilisateur.getTelephone());
                apprenant.setSexe(utilisateur.getSexe());
                apprenant.setEmail(utilisateur.getEmail());
                apprenant.setPassword(utilisateur.getPassword());
                apprenant.setRole("apprenant");
                apprenant.setEtat(utilisateur.getEtat());
                apprenantService.save(apprenant); // Sauvegarder un Apprenant
                
            } else if (utilisateur.getRole().equals("manager")) {
                Manager manager = new Manager();
                manager.setNom(utilisateur.getNom());
                manager.setPrenom(utilisateur.getPrenom());
                manager.setDateNaissance(utilisateur.getDateNaissance());
                manager.setTelephone(utilisateur.getTelephone());
                manager.setSexe(utilisateur.getSexe());
                manager.setEmail(utilisateur.getEmail());
                manager.setPassword(utilisateur.getPassword());
                manager.setRole("manager");
                manager.setEtat(utilisateur.getEtat());
                managerService.save(manager); // Sauvegarder un Manager
                
            } else if (utilisateur.getRole().equals("superadmin")) {
                SuperAdmin superAdmin = new SuperAdmin();
                superAdmin.setNom(utilisateur.getNom());
                superAdmin.setPrenom(utilisateur.getPrenom());
                superAdmin.setDateNaissance(utilisateur.getDateNaissance());
                superAdmin.setTelephone(utilisateur.getTelephone());
                superAdmin.setSexe(utilisateur.getSexe());
                superAdmin.setEmail(utilisateur.getEmail());
                superAdmin.setPassword(utilisateur.getPassword());
                superAdmin.setRole("superadmin");
                superAdmin.setEtat(utilisateur.getEtat());
                superAdminService.save(superAdmin); // Sauvegarder un SuperAdmin
                
            } else {
                return "Rôle non valide";
            }

            return "Inscription réussie en tant que " + utilisateur.getRole();
        }

}

