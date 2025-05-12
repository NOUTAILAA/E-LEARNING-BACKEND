package com.example.demo.repository;

import com.example.demo.entity.EtatChapitre;
import com.example.demo.entity.EtatChapitreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtatChapitreRepository extends JpaRepository<EtatChapitre, Long> {

@Query("SELECT e.id as id, e.etat as etat, e.apprenant.id as apprenantId, e.chapitre.id as chapitreId " +
       "FROM EtatChapitre e WHERE e.apprenant.id = :apprenantId")
List<EtatChapitreProjection> findByApprenantId(@Param("apprenantId") Long apprenantId);
}
