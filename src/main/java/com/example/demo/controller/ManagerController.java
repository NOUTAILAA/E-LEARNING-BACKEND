package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Departement;
import com.example.demo.entity.Manager;
import com.example.demo.service.DepartementService;
import com.example.demo.service.ManagerService;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private DepartementService departementService; // Injecter le service des départements

    @GetMapping
    public List<Manager> getAll() {
        return managerService.findAll();
    }
    @GetMapping("/departements")
    public List<Departement> getDepartements() {
        return departementService.findAll();  // Retourne la liste des départements
    }
    @PostMapping
    public Manager create(@RequestBody Manager manager) {
        return managerService.save(manager);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getById(@PathVariable Long id) {
        return managerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // Ajout du PUT Mapping pour mettre à jour un manager
    @PutMapping("/{id}")
    public Manager update(@PathVariable Long id, @RequestBody Manager managerDetails) {
        return managerService.findById(id)
                .map(manager -> {
                    // Mettre à jour les champs du manager avec les nouveaux détails
                    manager.setNom(managerDetails.getNom());
                    manager.setPrenom(managerDetails.getPrenom());
                    manager.setDateNaissance(managerDetails.getDateNaissance());
                    manager.setTelephone(managerDetails.getTelephone());
                    manager.setSexe(managerDetails.getSexe());
                    manager.setEmail(managerDetails.getEmail());
                    manager.setRole(managerDetails.getRole());
                    manager.setEtat(managerDetails.getEtat());
                    manager.setPhoto(managerDetails.getPhoto());
                    
                    // Mettre à jour le département
                    if (managerDetails.getDepartement() != null) {
                        Departement departement = departementService.findById(managerDetails.getDepartement().getId())
                            .orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
                        manager.setDepartement(departement);
                    }

                    return managerService.save(manager);  // Sauvegarder le manager mis à jour
                })
                .orElseThrow(() -> new IllegalArgumentException("Manager avec ID " + id + " non trouvé"));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        managerService.delete(id);
    }
}
