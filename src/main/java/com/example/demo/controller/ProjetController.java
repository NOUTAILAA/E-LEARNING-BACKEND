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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Departement;
import com.example.demo.entity.Projet;
import com.example.demo.service.DepartementService;
import com.example.demo.service.ProjetService;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Autowired
    private DepartementService departementService;

    @GetMapping
    public List<Projet> getAll() {
        return projetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getById(@PathVariable Long id) {
        return projetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Projet> create(@RequestBody Projet projet) {
        if (projet.getDepartement() != null) {
            Optional<Departement> dep = departementService.findById(projet.getDepartement().getId());
            if (dep.isPresent()) {
                projet.setDepartement(dep.get());
                return ResponseEntity.ok(projetService.save(projet));
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projet> update(@PathVariable Long id, @RequestBody Projet updatedProjet) {
        return projetService.findById(id)
                .map(projet -> {
                    projet.setNom(updatedProjet.getNom());
                    projet.setNomClient(updatedProjet.getNomClient());
                    projet.setDescription(updatedProjet.getDescription());
                    projet.setPhoto(updatedProjet.getPhoto());
                    if (updatedProjet.getDepartement() != null) {
                        projet.setDepartement(updatedProjet.getDepartement());
                    }
                    return ResponseEntity.ok(projetService.save(projet));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projetService.delete(id);
    }
    @PostMapping("/upload")
    public ResponseEntity<Projet> uploadProjetWithPhoto(
            @RequestPart("projet") Projet projet,
            @RequestPart("photo") MultipartFile file) {

        try {
            if (file != null && !file.isEmpty()) {
                projet.setPhoto(file.getBytes());
            }

            if (projet.getDepartement() != null) {
                Departement departement = departementService.findById(projet.getDepartement().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
                projet.setDepartement(departement);
            }

            Projet saved = projetService.save(projet);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/{id}/photo")
    public ResponseEntity<byte[]> getProjetPhoto(@PathVariable Long id) {
        Optional<Projet> optionalProjet = projetService.findById(id);

        if (optionalProjet.isPresent()) {
            Projet projet = optionalProjet.get();
            byte[] photo = projet.getPhoto();

            if (photo != null && photo.length > 0) {
                return ResponseEntity
                        .ok()
                        .header("Content-Type", "image/jpeg")  // ou image/png selon le type
                        .body(photo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
