package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Departement;
import com.example.demo.repository.DepartementRepository;

@Service
public class DepartementService {
    @Autowired
    private DepartementRepository departementRepository;

    public List<Departement> findAll() {
        return departementRepository.findAll();
    }

    public Departement save(Departement departement) {
        return departementRepository.save(departement);
    }

    public Optional<Departement> findById(Long id) {
        return departementRepository.findById(id);
    }

    public void delete(Long id) {
        departementRepository.deleteById(id);
    }
}
