package com.example.demo.repository;

import com.example.demo.entity.Section;
import com.example.demo.entity.SectionProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("SELECT s FROM Section s WHERE s.chapitre.id = :chapitreId")
    List<Section> findByChapitreId(Long chapitreId);



@Query("SELECT s.id AS id, s.titre AS titre, s.type AS type, s.description AS description, " +
       "s.file AS file, CAST(s.dateCreation AS string) AS dateCreation, " +
       "CAST(s.dateMAJ AS string) AS dateMAJ, s.tempsEstimer AS tempsEstimer, " +
       "s.chapitre.id AS chapitreId " +
       "FROM Section s WHERE s.chapitre.id = :chapitreId")
List<SectionProjection> findProjectedByChapitreId(Long chapitreId);



}
