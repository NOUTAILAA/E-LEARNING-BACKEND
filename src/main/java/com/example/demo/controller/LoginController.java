package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Admin;
import java.security.SecureRandom;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.Manager;
import com.example.demo.entity.SuperAdmin;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.ApprenantService;
import com.example.demo.service.EmailService;
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
            Optional<Apprenant> apprenantOpt = apprenantRepository.findByEmailIgnoreCase(email);
                if (apprenantOpt.isPresent()) {
                    Apprenant apprenant = apprenantOpt.get();
                    if (passwordEncoder.matches(password, apprenant.getPassword())) {
                        return ResponseEntity.ok("Bonjour " + apprenant.getNom() + ", vous êtes un " + apprenant.getRole());
                    }
                }
            Optional<Manager> managerOpt = managerRepository.findByEmailIgnoreCase(email);
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
@Autowired
private EmailService emailService;
@PostMapping("forgot-password")
public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
    String email = payload.get("email");
    String type = payload.get("type"); // peut être null
    String newPassword = genererMotDePasse();

    int count = 0;
    if (apprenantRepository.findByEmailIgnoreCase(email).isPresent()) count++;
    if (managerRepository.findByEmailIgnoreCase(email).isPresent()) count++;
    if (adminService.findByEmail(email).isPresent()) count++;
    if (superAdminService.findByEmail(email).isPresent()) count++;

    // ❌ Aucun utilisateur trouvé
    if (count == 0) {
        return ResponseEntity.status(404).body("Email introuvable.");
    }

    // ⚠️ Plusieurs utilisateurs trouvés → demander le type
    if (count > 1 && (type == null || type.isEmpty())) {
        return ResponseEntity.status(400).body("Plusieurs utilisateurs ont cet email. Veuillez préciser le type (apprenant, manager, admin, superadmin).");
    }

    // ✅ Apprenant
    if ("apprenant".equalsIgnoreCase(type) || (count == 1 && apprenantRepository.findByEmailIgnoreCase(email).isPresent())) {
        Apprenant a = apprenantRepository.findByEmailIgnoreCase(email).get();
        a.setPassword(passwordEncoder.encode(newPassword));
        apprenantRepository.save(a);
        emailService.envoyerMail(email, "Réinitialisation de mot de passe", "Votre nouveau mot de passe est : " + newPassword);
        return ResponseEntity.ok("✅ Nouveau mot de passe envoyé à l'apprenant.");
    }

    // ✅ Manager
    if ("manager".equalsIgnoreCase(type) || (count == 1 && managerRepository.findByEmailIgnoreCase(email).isPresent())) {
        Manager m = managerRepository.findByEmailIgnoreCase(email).get();
        m.setPassword(passwordEncoder.encode(newPassword));
        managerRepository.save(m);
        emailService.envoyerMail(email, "Réinitialisation de mot de passe", "Votre nouveau mot de passe est : " + newPassword);
        return ResponseEntity.ok("✅ Nouveau mot de passe envoyé au manager.");
    }

    // ✅ Admin
    if ("admin".equalsIgnoreCase(type) || (count == 1 && adminService.findByEmail(email).isPresent())) {
        Admin a = adminService.findByEmail(email).get();
        a.setPassword(passwordEncoder.encode(newPassword));
        adminService.save(a);
        emailService.envoyerMail(email, "Réinitialisation de mot de passe", "Votre nouveau mot de passe est : " + newPassword);
        return ResponseEntity.ok("✅ Nouveau mot de passe envoyé à l'admin.");
    }

    // ✅ SuperAdmin
    if ("superadmin".equalsIgnoreCase(type) || (count == 1 && superAdminService.findByEmail(email).isPresent())) {
        SuperAdmin s = superAdminService.findByEmail(email).get();
        s.setPassword(passwordEncoder.encode(newPassword));
        superAdminService.save(s);
        emailService.envoyerMail(email, "Réinitialisation de mot de passe", "Votre nouveau mot de passe est : " + newPassword);
        return ResponseEntity.ok("✅ Nouveau mot de passe envoyé au superadmin.");
    }

    return ResponseEntity.status(400).body("Type invalide ou non reconnu.");
}

private String genererMotDePasse() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < 10; i++) { // Génère un mot de passe de 10 caractères
        int index = random.nextInt(chars.length());
        sb.append(chars.charAt(index));
    }

    return sb.toString();
}


@PostMapping("reset-password")
public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) {
    String email = payload.get("email");
    String newPassword = payload.get("newPassword");

    // Cherche l'utilisateur par email et met à jour le mot de passe encodé
    Optional<Apprenant> apprenant = apprenantRepository.findByEmailIgnoreCase(email);
    if (apprenant.isPresent()) {
        Apprenant a = apprenant.get();
        a.setPassword(passwordEncoder.encode(newPassword));
        apprenantService.savee(a);
        return ResponseEntity.ok("Mot de passe apprenant mis à jour avec succès.");
    }

    Optional<Manager> manager = managerRepository.findByEmailIgnoreCase(email);
    if (manager.isPresent()) {
        Manager m = manager.get();
        m.setPassword(passwordEncoder.encode(newPassword));
        managerService.save(m);
        return ResponseEntity.ok("Mot de passe manager mis à jour avec succès.");
    }

    Optional<Admin> admin = adminService.findByEmail(email);
    if (admin.isPresent()) {
        Admin a = admin.get();
        a.setPassword(passwordEncoder.encode(newPassword));
        adminService.save(a);
        return ResponseEntity.ok("Mot de passe admin mis à jour avec succès.");
    }

    Optional<SuperAdmin> superAdmin = superAdminService.findByEmail(email);
    if (superAdmin.isPresent()) {
        SuperAdmin sa = superAdmin.get();
        sa.setPassword(passwordEncoder.encode(newPassword));
        superAdminService.save(sa);
        return ResponseEntity.ok("Mot de passe superadmin mis à jour avec succès.");
    }

    return ResponseEntity.status(404).body("Email introuvable.");
}

}

