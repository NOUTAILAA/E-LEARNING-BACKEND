package com.example.demo.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ApprenantRequestDTO;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Departement;
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
    public ResponseEntity<Apprenant> create(@RequestBody ApprenantRequestDTO dto) {
        Apprenant apprenant = new Apprenant();
        apprenant.setNom(dto.getNom());
        apprenant.setPrenom(dto.getPrenom());
        apprenant.setSexe(dto.getSexe());
        apprenant.setTelephone(dto.getTelephone());
        apprenant.setEmail(dto.getEmail());

        // Conversion String → Date
        LocalDate localDate = LocalDate.parse(dto.getDateNaissance());
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        apprenant.setDateNaissance(date);


        if (dto.getDepartementId() != null) {
            Departement departement = new Departement();
            departement.setId(dto.getDepartementId());
            apprenant.setDepartement(departement);
        }

        Apprenant saved = apprenantService.save(apprenant);
        return ResponseEntity.ok(saved);
    }

    // Récupérer un apprenant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Apprenant> getById(@PathVariable Long id) {
        Optional<Apprenant> apprenant = apprenantService.findById(id);
        return apprenant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un apprenant par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Apprenant> apprenant = apprenantService.findById(id);
        if (apprenant.isPresent()) {
            apprenantService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mettre à jour un apprenant par ID
   @PutMapping("/{id}")
public ResponseEntity<Apprenant> update(@PathVariable Long id, @RequestBody ApprenantRequestDTO dto) {
    Optional<Apprenant> optional = apprenantService.findById(id);
    if (optional.isEmpty()) return ResponseEntity.notFound().build();

    Apprenant existing = optional.get();
    String oldPassword = existing.getPassword();

    existing.setNom(dto.getNom());
    existing.setPrenom(dto.getPrenom());
    existing.setSexe(dto.getSexe());
    existing.setTelephone(dto.getTelephone());
    existing.setEmail(dto.getEmail());

    LocalDate localDate = LocalDate.parse(dto.getDateNaissance());
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    existing.setDateNaissance(date);

    if (dto.getDepartementId() != null) {
        Departement departement = new Departement();
        departement.setId(dto.getDepartementId());
        existing.setDepartement(departement);
    }

    // 🔐 NE PAS toucher au mot de passe
    existing.setPassword(oldPassword);

    Apprenant updated = apprenantService.update(existing);
    return ResponseEntity.ok(updated);
}
}