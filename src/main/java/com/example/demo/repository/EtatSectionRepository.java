package com.example.demo.repository;

import com.example.demo.entity.EtatSection;
import com.example.demo.entity.EtatSectionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EtatSectionRepository extends JpaRepository<EtatSection, Long> {

    @Query("SELECT es.id as id, es.etat as etat, " +
           "es.apprenant.id as apprenantId, es.apprenant.nom as apprenantNom, " +
           "es.section.id as sectionId, es.section.titre as sectionTitre " +
           "FROM EtatSection es WHERE es.apprenant.id = :apprenantId")
    List<EtatSectionProjection> findEtatSectionsByApprenantId(Long apprenantId);

List<EtatSection> findBySectionId(Long sectionId);
EtatSection findByApprenantIdAndSectionId(Long apprenantId , Long sectionId);
}