package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @GetMapping
    public List<Admin> getAll() {
        return adminService.findAll();
    }

    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return adminService.save(admin);
    }
    @GetMapping("/email")
    public Admin getByEmail(@RequestParam String email) {
        return adminRepository.findByEmailIgnoreCase(email).orElse(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getById(@PathVariable Long id) {
        return adminService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminService.delete(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        Optional<Admin> existingAdmin = adminService.findById(id);
        if (existingAdmin.isPresent()) {
            admin.setId(id);  // S'assurer que l'ID reste inchangé
            Admin updatedAdmin = adminService.save(admin);  // Sauvegarder l'administrateur mis à jour
            return ResponseEntity.ok(updatedAdmin);  // Retourner l'administrateur mis à jour
        } else {
            return ResponseEntity.notFound().build();  // Retourner 404 si l'administrateur n'existe pas
        }
    }

}