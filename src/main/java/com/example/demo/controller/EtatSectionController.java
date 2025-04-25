package com.example.demo.controller;

import com.example.demo.entity.EtatSectionProjection;
import com.example.demo.entity.EtatSectionRequestDTO;
import com.example.demo.entity.EtatSectionResponseDTO;
import com.example.demo.service.EtatSectionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/etat-section")
public class EtatSectionController {

    @Autowired
    private EtatSectionService etatSectionService;

 @PostMapping
public EtatSectionResponseDTO addOrUpdateEtatSection(@RequestBody EtatSectionRequestDTO request) {
    return etatSectionService.saveEtatSection(
        request.getApprenantId(),
        request.getSectionId(),
        request.isEtat()
    );
}
@GetMapping("/apprenant/{id}")
public List<EtatSectionProjection> getEtatSectionsByApprenant(@PathVariable Long id) {
    return etatSectionService.getEtatByApprenant(id);
}
}