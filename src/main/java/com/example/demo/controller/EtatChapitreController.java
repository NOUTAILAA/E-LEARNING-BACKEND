package com.example.demo.controller;

import com.example.demo.entity.EtatChapitreRequestDTO;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Chapitre;
import com.example.demo.entity.EtatChapitre;
import com.example.demo.entity.EtatChapitreDTO;
import com.example.demo.entity.EtatChapitreProjection;

import com.example.demo.repository.ApprenantRepository;
import com.example.demo.repository.ChapitreRepository;
import com.example.demo.repository.EtatChapitreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etatchapitres")
@CrossOrigin(origins = "*")
public class EtatChapitreController {

   @Autowired
    private ApprenantRepository apprenantRepository;
    @Autowired
    private ChapitreRepository chapitreRepository;
    @Autowired
    private EtatChapitreRepository etatChapitreRepository;

    @PostMapping
public ResponseEntity<EtatChapitreDTO> createEtat(@RequestBody EtatChapitreRequestDTO dto) {
    Apprenant apprenant = apprenantRepository.findById(dto.getApprenantId())
        .orElseThrow(() -> new RuntimeException("Apprenant non trouvé"));
    Chapitre chapitre = chapitreRepository.findById(dto.getChapitreId())
        .orElseThrow(() -> new RuntimeException("Chapitre non trouvé"));

    EtatChapitre etatChapitre = new EtatChapitre();
    etatChapitre.setEtat(dto.getEtat()); 
    etatChapitre.setApprenant(apprenant);
    etatChapitre.setChapitre(chapitre);

    EtatChapitre saved = etatChapitreRepository.save(etatChapitre);

    EtatChapitreDTO response = new EtatChapitreDTO(
        saved.getId(),
        saved.getEtat(),
        apprenant.getId(),
        chapitre.getId()
    );

    return ResponseEntity.ok(response);
}


    @GetMapping("/apprenant/{id}")
    public List<EtatChapitreProjection> getByApprenant(@PathVariable Long id) {
        return etatChapitreRepository.findByApprenantId(id);
    }
}
