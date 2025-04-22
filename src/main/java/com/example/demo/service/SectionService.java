package com.example.demo.service;

import com.example.demo.entity.Chapitre;
import com.example.demo.entity.Section;
import com.example.demo.entity.SectionDTO;
import com.example.demo.entity.SectionProjection;
import com.example.demo.entity.SectionRequestDTO;
import com.example.demo.repository.ChapitreRepository;
import com.example.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class SectionService {
    @Autowired
    private ChapitreRepository chapitreRepository;
    
    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElse(null);
    }

   

    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }

    public SectionDTO addSection(SectionRequestDTO dto) {
    Section section = new Section();
    section.setTitre(dto.getTitre());
    section.setType(dto.getType());
    section.setDescription(dto.getDescription());
    section.setFile(dto.getFile());
    section.setTempsEstimer(dto.getTempsEstimer());

    try {
        section.setDateCreation(dto.getDateCreation() != null ? java.sql.Date.valueOf(dto.getDateCreation()) : null);
        section.setDateMAJ(dto.getDateMAJ() != null ? java.sql.Date.valueOf(dto.getDateMAJ()) : null);
    } catch (Exception e) {
        throw new RuntimeException("Format de date invalide (attendu : yyyy-MM-dd)");
    }

    Chapitre chapitre = chapitreRepository.findById(dto.getChapitreId())
            .orElseThrow(() -> new RuntimeException("Chapitre introuvable"));
    section.setChapitre(chapitre);

    Section saved = sectionRepository.save(section);
    return new SectionDTO(saved);
}
public List<SectionProjection> getProjectedSectionsByChapitreId(Long chapitreId) {
    return sectionRepository.findProjectedByChapitreId(chapitreId);
}










public List<SectionDTO> getSectionsByChapitreId(Long chapitreId) {
    List<Section> sections = sectionRepository.findByChapitreId(chapitreId);
    return sections.stream().map(SectionDTO::new).toList();
}

}