package com.TP3.repository;

import com.TP3.entities.Utilisateur;
import com.TP3.entities.UtilisateurImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurImageRepository extends JpaRepository<UtilisateurImage, Long> {

    // Recherche d'une image liée à un Utilisateur spécifique
    Optional<UtilisateurImage> findByUtilisateur(Utilisateur utilisateur);
}
