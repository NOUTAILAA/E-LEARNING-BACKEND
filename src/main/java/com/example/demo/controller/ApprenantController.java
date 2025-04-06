package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Apprenant;
import com.example.demo.service.ApprenantService;

@RestController
@RequestMapping("/api/apprenants")
public class ApprenantController {

    @Autowired
    private ApprenantService apprenantService;

    // Récupérer tous les apprenants
    @GetMapping
    public List<Apprenant> getAll() {
        return apprenantService.findAll();
    }

    // Créer un nouvel apprenant
    @PostMapping
    public Apprenant create(@RequestBody Apprenant apprenant) {
        return apprenantService.save(apprenant);
    }

    // Récupérer un apprenant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Apprenant> getById(@PathVariable Long id) {
        Optional<Apprenant> apprenant = apprenantService.findById(id);
        if (apprenant.isPresent()) {
            return ResponseEntity.ok(apprenant.get());
        } else {
            return ResponseEntity.notFound().build();  // Retourne une réponse 404 si l'apprenant n'est pas trouvé
        }
    }

    // Supprimer un apprenant par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Apprenant> apprenant = apprenantService.findById(id);
        if (apprenant.isPresent()) {
            apprenantService.delete(id);  // Supprimer l'apprenant de la base de données
            return ResponseEntity.noContent().build();  // Retourne une réponse 204 si la suppression est réussie
        } else {
            return ResponseEntity.notFound().build();  // Retourne une réponse 404 si l'apprenant n'est pas trouvé
        }
    }

    // Mettre à jour un apprenant par ID
    @PutMapping("/{id}")
    public ResponseEntity<Apprenant> update(@PathVariable Long id, @RequestBody Apprenant apprenantDetails) {
        Optional<Apprenant> optionalApprenant = apprenantService.findById(id);
        if (optionalApprenant.isPresent()) {
            Apprenant apprenant = optionalApprenant.get();
            apprenant.setNom(apprenantDetails.getNom());
            apprenant.setPrenom(apprenantDetails.getPrenom());
            apprenant.setDateNaissance(apprenantDetails.getDateNaissance());
            apprenant.setTelephone(apprenantDetails.getTelephone());
            apprenant.setSexe(apprenantDetails.getSexe());
            apprenant.setEmail(apprenantDetails.getEmail());
            apprenant.setPassword(apprenantDetails.getPassword());
            apprenant.setDepartement(apprenantDetails.getDepartement());

            // Sauvegarder les modifications
            Apprenant updatedApprenant = apprenantService.save(apprenant);
            return ResponseEntity.ok(updatedApprenant);  // Retourne l'apprenant mis à jour
        } else {
            return ResponseEntity.notFound().build();  // Retourne une réponse 404 si l'apprenant n'est pas trouvé
        }
    }
}
