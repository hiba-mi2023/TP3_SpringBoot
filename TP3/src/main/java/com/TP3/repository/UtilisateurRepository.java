package com.TP3.repository;

import com.TP3.entities.Role;
import com.TP3.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Recherche d'un Utilisateur par email
    Optional<Utilisateur> findByEmail(String email);

    // Récupération des Utilisateurs associés à un Rôle spécifique
    List<Utilisateur> findByRole(Role role);
}
