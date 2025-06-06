package com.example.demo.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ManagerRequestDTO;
import com.example.demo.entity.Apprenant;
import com.example.demo.entity.Departement;
import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.DepartementService;
import com.example.demo.service.ManagerService;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private DepartementService departementService; // Injecter le service des départements
    @Autowired
    private ManagerRepository managerRepository;
    @GetMapping
    public List<Manager> getAll() {
        return managerService.findAll();
    }
    @GetMapping("/departements")
    public List<Departement> getDepartements() {
        return departementService.findAll();  // Retourne la liste des départements
    }
  
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getById(@PathVariable Long id) {
        return managerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
public ResponseEntity<Manager> update(@PathVariable Long id, @RequestBody ManagerRequestDTO dto) {
    return managerService.findById(id)
        .map(manager -> {
            manager.setNom(dto.getNom());
            manager.setPrenom(dto.getPrenom());
            manager.setEmail(dto.getEmail());
            manager.setTelephone(dto.getTelephone());
            manager.setSexe(dto.getSexe());
            manager.setRole(dto.getRole());

            // Conversion date string → java.util.Date
            LocalDate localDate = LocalDate.parse(dto.getDateNaissance());
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            manager.setDateNaissance(date);

            // Mettre à jour le département
            if (dto.getDepartementId() != null) {
                Departement departement = departementService.findById(dto.getDepartementId())
                        .orElseThrow(() -> new IllegalArgumentException("Département non trouvé"));
                manager.setDepartement(departement);
            }

            return ResponseEntity.ok(managerService.save(manager));
        })
        .orElse(ResponseEntity.notFound().build());
}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        managerService.delete(id);
    }
    @PostMapping("/{managerId}/assign-apprenants")
public ResponseEntity<?> assignApprenants(
        @PathVariable Long managerId,
        @RequestBody List<Long> apprenantIds) {
    Manager manager = managerService.assignApprenantsToManager(managerId, apprenantIds);
    return ResponseEntity.ok(manager);
}
@GetMapping("/{id}/apprenants")
public ResponseEntity<List<Apprenant>> getApprenantsByManager(@PathVariable Long id) {
    return managerService.findById(id)
            .map(manager -> ResponseEntity.ok(manager.getApprenants()))
            .orElse(ResponseEntity.notFound().build());
}
@DeleteMapping("/{managerId}/unassign-apprenant/{apprenantId}")
public ResponseEntity<?> unassignApprenant(
        @PathVariable Long managerId,
        @PathVariable Long apprenantId) {
    try {
        managerService.unassignApprenant(managerId, apprenantId);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Erreur lors de la désassignation");
    }
}

@GetMapping("/{managerId}/apprenants-non-assignes")
public List<Apprenant> getApprenantsNonAssignesDuDepartement(@PathVariable Long managerId) {
    Manager manager = managerService.findById(managerId)
        .orElseThrow(() -> new IllegalArgumentException("Manager non trouvé"));
    
    Long departementId = manager.getDepartement().getId();
    return managerService.getApprenantsNonAssignesDansDepartement(departementId);
}
@GetMapping("/email/{email}/apprenants")
public ResponseEntity<List<Apprenant>> getApprenantsByManagerEmail(@PathVariable String email) {
    Optional<Manager> managerOpt = managerService.findByEmail(email);
    if (managerOpt.isPresent()) {
        return ResponseEntity.ok(managerOpt.get().getApprenants());
    } else {
        return ResponseEntity.notFound().build();
    }
}
@GetMapping("/email")
public ResponseEntity<Manager> getByEmail(@RequestParam String email) {
    Optional<Manager> manager = managerRepository.findByEmailIgnoreCase(email);
    return manager.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
}

@PostMapping
public ResponseEntity<Manager> create(@RequestBody ManagerRequestDTO dto) {
    Manager manager = new Manager();
    manager.setNom(dto.getNom());
    manager.setPrenom(dto.getPrenom());
    manager.setEmail(dto.getEmail());
    manager.setTelephone(dto.getTelephone());
    manager.setSexe(dto.getSexe());
     // Conversion String → Date
        LocalDate localDate = LocalDate.parse(dto.getDateNaissance());
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        manager.setDateNaissance(date);

    manager.setRole(dto.getRole());

    if (dto.getDepartementId() != null) {
        Departement departement = departementService.findById(dto.getDepartementId())
                .orElseThrow(() -> new IllegalArgumentException("Département introuvable"));
        manager.setDepartement(departement);
    }

    return ResponseEntity.ok(managerService.save(manager));
}
}
