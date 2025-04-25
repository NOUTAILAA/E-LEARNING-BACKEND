package com.example.demo.service;

import com.example.demo.entity.EtatSection;
import com.example.demo.entity.EtatSectionProjection;
import com.example.demo.entity.EtatSectionResponseDTO;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Section;
import com.example.demo.repository.EtatSectionRepository;
import com.example.demo.repository.ApprenantRepository;
import com.example.demo.repository.SectionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtatSectionService {

    @Autowired
    private EtatSectionRepository etatSectionRepository;

    @Autowired
    private ApprenantRepository apprenantRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public EtatSectionResponseDTO saveEtatSection(Long apprenantId, Long sectionId, boolean etat) {
        Apprenant apprenant = apprenantRepository.findById(apprenantId).orElseThrow();
        Section section = sectionRepository.findById(sectionId).orElseThrow();
    
        EtatSection existing = etatSectionRepository.findByApprenantIdAndSectionId(apprenantId, sectionId);
    
        EtatSection etatSection;
        if (existing != null) {
            // Relation existe : on met juste à jour
            etatSection = existing;
            etatSection.setEtat(etat);
        } else {
            // Relation n'existe pas : on crée une nouvelle
            etatSection = new EtatSection();
            etatSection.setApprenant(apprenant);
            etatSection.setSection(section);
            etatSection.setEtat(etat);
        }
    
        EtatSection saved = etatSectionRepository.save(etatSection);
    
        EtatSectionResponseDTO dto = new EtatSectionResponseDTO();
        dto.setId(saved.getId());
        dto.setEtat(saved.isEtat());
        dto.setApprenantId(saved.getApprenant().getId());
        dto.setApprenantNom(saved.getApprenant().getNom());
        dto.setSectionId(saved.getSection().getId());
        dto.setSectionTitre(saved.getSection().getTitre());
        return dto;
    }
public List<EtatSectionProjection> getEtatByApprenant(Long apprenantId) {
    return etatSectionRepository.findEtatSectionsByApprenantId(apprenantId);
}
}