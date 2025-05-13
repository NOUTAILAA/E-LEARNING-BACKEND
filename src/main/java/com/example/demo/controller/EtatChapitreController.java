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
import com.example.demo.repository.EtatSectionRepository;

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
@Autowired
private EtatSectionRepository etatSectionRepository;




    @GetMapping("/apprenant/{id}")
    public List<EtatChapitreProjection> getByApprenant(@PathVariable Long id) {
        return etatChapitreRepository.findByApprenantId(id);
    }
    @PostMapping
public ResponseEntity<?> createOrUpdateEtat(@RequestBody EtatChapitreRequestDTO dto) {
    Apprenant apprenant = apprenantRepository.findById(dto.getApprenantId())
        .orElseThrow(() -> new RuntimeException("Apprenant non trouvé"));
    Chapitre chapitre = chapitreRepository.findById(dto.getChapitreId())
        .orElseThrow(() -> new RuntimeException("Chapitre non trouvé"));

    // 🔒 Si etat = true, on vérifie toutes les sections
    if (dto.getEtat()) {
        int nonValidees = etatSectionRepository.countSectionsNonValidees(dto.getApprenantId(), dto.getChapitreId());
        if (nonValidees > 0) {
            return ResponseEntity.badRequest()
                .body("Impossible de valider ce chapitre : toutes les sections ne sont pas encore validées.");
        }
    }

    EtatChapitre existing = etatChapitreRepository.findByApprenantIdAndChapitreId(dto.getApprenantId(), dto.getChapitreId());
    EtatChapitre saved;

    if (existing != null) {
        existing.setEtat(dto.getEtat());
        saved = etatChapitreRepository.save(existing);
    } else {
        EtatChapitre etatChapitre = new EtatChapitre();
        etatChapitre.setEtat(dto.getEtat());
        etatChapitre.setApprenant(apprenant);
        etatChapitre.setChapitre(chapitre);
        saved = etatChapitreRepository.save(etatChapitre);
    }

    return ResponseEntity.ok(new EtatChapitreDTO(
        saved.getId(), saved.getEtat(), apprenant.getId(), chapitre.getId()
    ));
}
@GetMapping("/test/countNonValidees")
public int testCountNonValidees(@RequestParam Long apprenantId, @RequestParam Long chapitreId) {
    return etatSectionRepository.countSectionsNonValidees(apprenantId, chapitreId);
}

}
