package com.example.demo.service;

import com.example.demo.entity.EtatChapitreRequestDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtatChapitreService {

    @Autowired
    private EtatChapitreRepository etatChapitreRepository;

    @Autowired
    private ApprenantRepository apprenantRepository;

    @Autowired
    private ChapitreRepository chapitreRepository;

    public EtatChapitre saveEtat(EtatChapitreRequestDTO dto) {
        Apprenant apprenant = apprenantRepository.findById(dto.getApprenantId()).orElseThrow();
        Chapitre chapitre = chapitreRepository.findById(dto.getChapitreId()).orElseThrow();

        EtatChapitre etat = new EtatChapitre();
        etat.setEtat(dto.getEtat());
        etat.setApprenant(apprenant);
        etat.setChapitre(chapitre);

        return etatChapitreRepository.save(etat);
    }
}
