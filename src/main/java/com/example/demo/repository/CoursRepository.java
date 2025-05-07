package com.example.demo.repository;

import com.example.demo.entity.Cours;
import com.example.demo.entity.CoursDTO;
import com.example.demo.entity.CoursProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    @Query("SELECT c.id as id, c.titre as titre, c.description as description, " +
    "c.tempsEstimer as tempsEstimer, c.dateCreation as dateCreation, " +
    "c.dateMAJ as dateMAJ, c.projet.nom as projetNom " +
    "FROM Cours c WHERE c.projet.id = :projetId" +
    " ORDER BY c.id ASC"
    )
List<CoursProjection> findCoursSansProjet(@Param("projetId") Long projetId);
//List<Cours> findByProjetId(Long projetId);


@Query("""
    SELECT c.id AS id, c.titre AS titre, c.description AS description, 
           c.tempsEstimer AS tempsEstimer, c.projet.nom AS projetNom
    FROM Cours c
    WHERE c.projet.departement.id = :departementId
    AND c.id NOT IN (
        SELECT DISTINCT s.chapitre.cours.id
        FROM EtatSection es
        JOIN es.section s
        WHERE es.apprenant.id = :apprenantId AND es.etat = true
    )
""")
List<CoursProjection> findCoursNonConsultesByApprenant(Long apprenantId, Long departementId); 




@Query("""
    SELECT c FROM Cours c
    WHERE NOT EXISTS (
        SELECT s FROM Section s
        JOIN s.chapitre ch
        WHERE ch.cours = c
        AND NOT EXISTS (
            SELECT es FROM EtatSection es
            WHERE es.apprenant.id = :apprenantId
            AND es.section = s
            AND es.etat = true
        )
    )
""")
List<Cours> findCoursTerminesParApprenant(@Param("apprenantId") Long apprenantId);

@Query("""
    SELECT c FROM Cours c
    JOIN c.chapitres ch
    JOIN ch.sections s
    WHERE c.projet.departement.id = (
        SELECT a.departement.id FROM Apprenant a WHERE a.id = :apprenantId
    )
    AND NOT EXISTS (
        SELECT es FROM EtatSection es
        WHERE es.section = s AND es.apprenant.id = :apprenantId AND es.etat = false
    )
    GROUP BY c
""")
List<Cours> findCoursTermines(@Param("apprenantId") Long apprenantId);

@Query("""
    SELECT DISTINCT new com.example.demo.entity.CoursDTO(
        c.id, c.titre, c.description, c.tempsEstimer, c.projet.id, c.projet.nom
    )
    FROM Cours c
    JOIN c.chapitres ch
    JOIN ch.sections s
    JOIN EtatSection es ON es.section.id = s.id
    WHERE es.apprenant.id = :apprenantId AND es.etat = true
""")
List<CoursDTO> findCoursConsultesParApprenant(@Param("apprenantId") Long apprenantId);

}
