package com.example.demo.controller;

import com.example.demo.entity.Chapitre;
import com.example.demo.entity.ChapitreDTO;
import com.example.demo.entity.ChapitreProjection;
import com.example.demo.entity.Cours;
import com.example.demo.repository.CoursRepository;
import com.example.demo.service.ChapitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/chapitres")
public class ChapitreController {
    @Autowired
    private CoursRepository coursRepository;
    
    
    

    @Autowired
    private ChapitreService chapitreService;

  /*   @GetMapping
    public List<Chapitre> getAllChapitres() {
        return chapitreService.getAllChapitres();
    }
*/
    @GetMapping("/{id}")
    public Chapitre getChapitreById(@PathVariable Long id) {
        return chapitreService.getChapitreById(id);
    }

  /* @GetMapping("/cours/{coursId}")
    public ResponseEntity<List<ChapitreDTO>> getChapitresByCoursId(@PathVariable Long coursId) {
        List<Chapitre> chapitres = chapitreService.getChapitresByCoursId(coursId);
        List<ChapitreDTO> result = chapitres.stream()
            .map(ChapitreDTO::new)
            .toList();
        return ResponseEntity.ok(result);
    }*/
@GetMapping("/cours/{coursId}")
public ResponseEntity<List<ChapitreProjection>> getLightChapitresByCours(@PathVariable Long coursId) {
    List<ChapitreProjection> chapitres = chapitreService.getLightChapitresByCoursId(coursId);
    return ResponseEntity.ok(chapitres);
}
  
    @GetMapping
    public ResponseEntity<List<ChapitreDTO>> getAllChapitres() {
        List<Chapitre> chapitres = chapitreService.getAllChapitres();
        List<ChapitreDTO> result = chapitres.stream()
            .map(ChapitreDTO::new)
            .toList();
        return ResponseEntity.ok(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ChapitreDTO> updateChapitre(@PathVariable Long id, @RequestBody ChapitreRequestDTO dto) {
        Chapitre existing = chapitreService.getChapitreById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
    
        existing.setTitre(dto.getTitre());
        existing.setTempsEstimer(dto.getTempsEstimer());
    
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (dto.getDateCreation() != null) {
                existing.setDateCreation(sdf.parse(dto.getDateCreation()));
            }
            if (dto.getDateMAJ() != null) {
                existing.setDateMAJ(sdf.parse(dto.getDateMAJ()));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    
        // mise à jour du cours lié si nécessaire
        if (dto.getCoursId() != null) {
            Cours cours = coursRepository.findById(dto.getCoursId()).orElse(null);
            if (cours != null) {
                existing.setCours(cours);
            }
        }
    
        Chapitre updated = chapitreService.saveChapitre(existing);
        return ResponseEntity.ok(new ChapitreDTO(updated));
    }
    
    
   
    @DeleteMapping("/{id}")
    public void deleteChapitre(@PathVariable Long id) {
        chapitreService.deleteChapitre(id);
    }


    @PostMapping
public ResponseEntity<ChapitreDTO> createChapitre(@RequestBody ChapitreRequestDTO dto) {
    Cours cours = coursRepository.findById(dto.getCoursId())
        .orElseThrow(() -> new RuntimeException("Cours introuvable"));

    Chapitre chapitre = new Chapitre();
    chapitre.setTitre(dto.getTitre());
    chapitre.setTempsEstimer(dto.getTempsEstimer());
    chapitre.setCours(cours);

    // Convertir les dates depuis String vers java.util.Date
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (dto.getDateCreation() != null) {
            chapitre.setDateCreation(sdf.parse(dto.getDateCreation()));
        }
        if (dto.getDateMAJ() != null) {
            chapitre.setDateMAJ(sdf.parse(dto.getDateMAJ()));
        }
    } catch (Exception e) {
        return ResponseEntity.badRequest().build();
    }

    Chapitre saved = chapitreService.saveChapitre(chapitre);
    return ResponseEntity.ok(new ChapitreDTO(saved));
}



}
