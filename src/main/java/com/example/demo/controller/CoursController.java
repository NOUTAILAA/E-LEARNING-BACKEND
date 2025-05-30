package com.example.demo.controller;
import java.util.Date;

import com.example.demo.entity.Cours;
import com.example.demo.entity.CoursDTO;
import com.example.demo.entity.CoursProjection;
import com.example.demo.entity.CoursRequestDTO;
import com.example.demo.entity.Projet;
import com.example.demo.repository.ProjetRepository;
import com.example.demo.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*") // ⬅️ Pour autoriser les appels depuis ton frontend/curl

@RestController
@RequestMapping("/api/cours")
public class CoursController {
@Autowired
private ProjetRepository projetRepository;

    @Autowired
    private CoursService coursService;

    @GetMapping
    public List<Cours> getAllCours() {
        return coursService.getAllCours();
    }

    @GetMapping("/{id}")
    public Cours getCoursById(@PathVariable Long id) {
        return coursService.getCoursById(id);
    }

  /*   @GetMapping("/projet/{projetId}")
public ResponseEntity<List<CoursDTO>> getCoursByProjet(@PathVariable Long projetId) {
    try {
        List<Cours> coursList = coursService.getCoursByProjetId(projetId);
        List<CoursDTO> dtoList = coursList.stream().map(CoursDTO::new).toList();
        return ResponseEntity.ok(dtoList);
    } catch (Exception e) {
        e.printStackTrace(); // log d’erreur utile
        return ResponseEntity.internalServerError().build();
    }
}*/
@GetMapping("/projet/{projetId}")
public ResponseEntity<List<CoursProjection>> getCoursByProjet(@PathVariable Long projetId) {
    try {
        return ResponseEntity.ok(coursService.getCoursSansProjet(projetId));
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}


/*
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public Cours createCours(@RequestBody Cours cours) {
    cours.setId(null);  // très important pour éviter la duplication
    System.out.println("Projet reçu : " + cours.getProjet());  // ✅ Debug

    // 🔵 Récupérer le projet à partir de l’ID envoyé
    if (cours.getProjet() != null && cours.getProjet().getId() != null) {
        Projet projet = projetRepository.findById(cours.getProjet().getId())
                            .orElseThrow(() -> new RuntimeException("Projet introuvable"));
        cours.setProjet(projet);
    } else {
        throw new RuntimeException("Projet non spécifié");
    }

    return coursService.saveCours(cours);
} */
@PostMapping
public ResponseEntity<CoursDTO> createCours(@RequestBody CoursRequestDTO dto) {
    if (dto.getProjetId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Projet projet = projetRepository.findById(dto.getProjetId())
        .orElseThrow(() -> new RuntimeException("Projet introuvable"));

    Cours cours = new Cours();
    cours.setTitre(dto.getTitre());
    cours.setDescription(dto.getDescription());
    cours.setTempsEstimer(dto.getTempsEstimer());
    cours.setProjet(projet);

    // Optionnel : setDateCreation si tu veux le faire automatiquement
    cours.setDateCreation(new java.util.Date());

    Cours saved = coursService.saveCours(cours);
    return ResponseEntity.ok(new CoursDTO(saved));
}

    @DeleteMapping("/{id}")
    public void deleteCours(@PathVariable Long id) {
        coursService.deleteCours(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CoursDTO> updateCours(@PathVariable Long id, @RequestBody CoursRequestDTO dto) {
        Cours cours = coursService.getCoursById(id);
        if (cours == null) {
            return ResponseEntity.notFound().build();
        }
    
        cours.setTitre(dto.getTitre());
        cours.setDescription(dto.getDescription());
        cours.setTempsEstimer(dto.getTempsEstimer());
        cours.setDateMAJ(new Date());
    
        cours = coursService.saveCours(cours);
        return ResponseEntity.ok(new CoursDTO(cours));
    }
    @GetMapping("/non-consultes/apprenant/{id}")
    public List<CoursProjection> getCoursNonConsultes(@PathVariable Long id) {
        return coursService.getCoursNonConsultesParApprenant(id);
    }

    @GetMapping("/termines/apprenant/{id}")
    public List<Cours> getCoursTermines(@PathVariable Long id) {
        return coursService.findCoursTerminesByApprenant(id);
    }
    @GetMapping("/consultes/apprenant/{id}")
    public List<CoursDTO> getCoursConsultes(@PathVariable Long id) {
        return coursService.getCoursConsultesParApprenant(id);
    }
    
}