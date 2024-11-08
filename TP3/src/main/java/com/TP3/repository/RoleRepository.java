package com.TP3.repository;

import com.TP3.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Recherche d'un Rôle par nom
    Optional<Role> findByNom(String nom);
}
