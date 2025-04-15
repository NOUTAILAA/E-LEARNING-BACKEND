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
import com.example.demo.entity.Manager;
import com.example.demo.entity.Projet;
import com.example.demo.entity.ProjetDTO;
import com.example.demo.service.DepartementService;
import com.example.demo.service.ProjetService;

import org.springframework.transaction.annotation.Transactional;


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

    @GetMapping("/manager/{managerId}")
    @Transactional
public ResponseEntity<List<ProjetDTO>> getByManager(@PathVariable Long managerId) {
    try {
        List<Projet> projets = projetService.findByManagerId(managerId);
        List<ProjetDTO> dtos = projets.stream().map(ProjetDTO::new).toList();
        return ResponseEntity.ok(dtos);
    } catch (Exception e) {
        e.printStackTrace(); // ✅ Tu verras l’erreur exacte dans la console backend
        return ResponseEntity.internalServerError().build();
    }
}

    


@PostMapping
public ResponseEntity<Projet> create(@RequestBody ProjetDTO dto) {
    Projet projet = new Projet();
    projet.setNom(dto.getNom());
    projet.setNomClient(dto.getNomClient());
    projet.setDescription(dto.getDescription());

    // Récupérer et affecter le Manager
    if (dto.getManagerId() != null) {
        Manager manager = projetService.getManagerById(dto.getManagerId());
        projet.setManager(manager);
    }

    // Récupérer et affecter le Département
    if (dto.getDepartementId() != null) {
        Departement dept = projetService.getDepartementById(dto.getDepartementId());
        projet.setDepartement(dept);
    }

    Projet saved = projetService.save(projet);
    return ResponseEntity.ok(saved);
}


@PutMapping("/{id}")
public ResponseEntity<Projet> update(@PathVariable Long id, @RequestBody ProjetDTO updatedDTO) {
    return projetService.findById(id)
            .map(projet -> {
                // Mise à jour des champs simples
                projet.setNom(updatedDTO.getNom());
                projet.setNomClient(updatedDTO.getNomClient());
                projet.setDescription(updatedDTO.getDescription());

                // Mise à jour du manager
                if (updatedDTO.getManagerId() != null) {
                    Manager manager = projetService.getManagerById(updatedDTO.getManagerId());
                    projet.setManager(manager);
                }

                // Mise à jour du département
                if (updatedDTO.getDepartementId() != null) {
                    Departement dept = projetService.getDepartementById(updatedDTO.getDepartementId());
                    projet.setDepartement(dept);
                }

                // ⚠️ Ne pas toucher à la photo ici, elle est gérée via l’endpoint /{id}/upload-image
                Projet updated = projetService.save(projet);
                return ResponseEntity.ok(updated);
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
            @RequestPart(value = "photo", required = false) MultipartFile file) {
    
        try {
            // Gérer la photo si elle est présente
            if (file != null && !file.isEmpty()) {
                projet.setPhoto(file.getBytes());
            }
    
            // Charger le département si fourni
            if (projet.getDepartement() != null && projet.getDepartement().getId() != null) {
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

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<Projet> uploadImage(@PathVariable Long id, @RequestPart("photo") MultipartFile file) {
        Optional<Projet> optionalProjet = projetService.findById(id);
    
        if (optionalProjet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        Projet projet = optionalProjet.get();
        try {
            projet.setPhoto(file.getBytes());
            Projet updated = projetService.save(projet);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
}