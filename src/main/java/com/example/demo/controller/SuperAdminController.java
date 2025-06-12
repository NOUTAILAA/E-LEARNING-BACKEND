package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SuperAdmin;
import com.example.demo.service.SuperAdminService;
import com.example.demo.repository.SuperAdminRepository;

@RestController
@RequestMapping("/api/superadmins")
public class SuperAdminController {
    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private SuperAdminRepository superAdminRepository;

    @GetMapping
    public List<SuperAdmin> getAll() {
        return superAdminService.findAll();
    }
    @GetMapping("/email")
    public SuperAdmin getByEmail(@RequestParam String email) {
        return superAdminRepository.findByEmailIgnoreCase(email).orElse(null);
    }

    @PostMapping
    public SuperAdmin create(@RequestBody SuperAdmin superAdmin) {
        return superAdminService.save(superAdmin);
    }
    
@PutMapping("/{id}")
public ResponseEntity<SuperAdmin> updateAdmin(@PathVariable Long id, @RequestBody SuperAdmin admin) {
    Optional<SuperAdmin> existingAdminOpt = superAdminService.findById(id);

    if (existingAdminOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    SuperAdmin existingAdmin = existingAdminOpt.get();

    // 🛡️ Ne pas toucher au mot de passe existant
    String oldPassword = existingAdmin.getPassword();

    admin.setId(id);
    admin.setPassword(oldPassword);  // 🧠 Réutiliser l'ancien mot de passe

    // Optionnel : conserver aussi le rôle actuel si non présent dans la requête
    if (admin.getRole() == null || admin.getRole().isEmpty()) {
        admin.setRole(existingAdmin.getRole());
    }

    SuperAdmin updated = superAdminService.save(admin);
    return ResponseEntity.ok(updated);
}

}

