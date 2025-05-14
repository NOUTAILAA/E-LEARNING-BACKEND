package com.example.demo.controller;

import com.example.demo.entity.Compteur;
import com.example.demo.entity.CompteurRequestDTO;
import com.example.demo.entity.CompteurResponseDTO;
import com.example.demo.service.CompteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compteurs")
@CrossOrigin(origins = "*")
public class CompteurController {

    @Autowired
    private CompteurService compteurService;

@PostMapping
public CompteurResponseDTO addCompteur(@RequestBody CompteurRequestDTO dto) {
    Compteur compteur = compteurService.saveFromDTO(dto);
    CompteurResponseDTO response = new CompteurResponseDTO();
    response.setId(compteur.getId());
    response.setApprenantId(compteur.getApprenant().getId());
    response.setSectionId(compteur.getSection() != null ? compteur.getSection().getId() : null);
    response.setQuizSectionId(compteur.getQuizSection() != null ? compteur.getQuizSection().getId() : null);
    response.setQuizChapitreId(compteur.getQuizChapitre() != null ? compteur.getQuizChapitre().getId() : null);
    response.setTempsPasse(compteur.getTempsPasse());
    return response;
}


    @GetMapping
    public List<Compteur> getAll() {
        return compteurService.getAllCompteurs();
    }

    @GetMapping("/{id}")
    public Compteur getById(@PathVariable Long id) {
        return compteurService.getCompteurById(id);
    }
}
