package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Departement;
import com.example.demo.entity.Manager;
import com.example.demo.entity.Projet;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.repository.ProjetRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetService {

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private ManagerRepository managerRepository;

    public List<Projet> findAll() {
        return projetRepository.findAll();
    }

    public Optional<Projet> findById(Long id) {
        return projetRepository.findById(id);
    }

    @Transactional(readOnly = true)
public List<Projet> findByManagerId(Long managerId) {
    return projetRepository.findByManagerId(managerId);
}

    
//  affecter un projet a un manager .
    public Projet save(Projet projet) {
        if (projet.getManager() != null) {
            Manager manager = managerRepository.findById(projet.getManager().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Manager non trouvé"));
            projet.setManager(manager);
        }
        return projetRepository.save(projet);
    }

    public void delete(Long id) {
        projetRepository.deleteById(id);
    }

@Autowired
private DepartementService departementService;

public Manager getManagerById(Long id) {
    return managerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Manager introuvable"));
}

public Departement getDepartementById(Long id) {
    return departementService.findById(id)
            .orElseThrow(() -> new RuntimeException("Département introuvable"));
}

}
