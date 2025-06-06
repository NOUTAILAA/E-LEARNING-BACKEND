package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Apprenant;

@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    Optional<Apprenant> findByEmailAndPassword(String email, String password);
    Optional<Apprenant> findByEmailIgnoreCase(String email);
    List<Apprenant> findByManagerIsNullAndDepartementId(Long departementId);

}

