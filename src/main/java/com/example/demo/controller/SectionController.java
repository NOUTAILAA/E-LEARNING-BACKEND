package com.example.demo.controller;

import com.example.demo.entity.Section;
import com.example.demo.entity.SectionDTO;
import com.example.demo.entity.SectionProjection;
import com.example.demo.entity.SectionRequestDTO;
import com.example.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {
        return sectionService.getAllSections();
    }

    @GetMapping("/{id}")
    public Section getSectionById(@PathVariable Long id) {
        return sectionService.getSectionById(id);
    }

  

    @PostMapping
    public Section createSection(@RequestBody Section section) {
        return sectionService.saveSection(section);
    }

    @DeleteMapping("/{id}")
    public void deleteSection(@PathVariable Long id) {
        sectionService.deleteSection(id);
    }

    @PostMapping("/add")
    public SectionDTO createSection(@RequestBody SectionRequestDTO dto) {
        return sectionService.addSection(dto);
    }
    @GetMapping("/chapitre/{chapitreId}")
    public List<SectionProjection> getSectionsByChapitreId(@PathVariable Long chapitreId) {
        return sectionService.getProjectedSectionsByChapitreId(chapitreId);
    }
    @GetMapping("/nouveau/{id}")
public SectionDTO getSectionByIdN(@PathVariable Long id) {
    return sectionService.getSectionDTOById(id);
}

}
