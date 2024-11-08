package com.TP3.controller;
import com.TP3.entities.Utilisateur;
import com.TP3.entities.UtilisateurImage;
import com.TP3.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // 1) Récupération de tous les Utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // 2) Création d'un nouvel Utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.creerUtilisateur(utilisateur.getEmail(), utilisateur.getNom(), utilisateur.getRole().getNom());
        return ResponseEntity.ok(createdUtilisateur);
    }

    // 3) Récupération d'un Utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(utilisateur);
    }

    // 4) Association d'un rôle à un Utilisateur
    @PutMapping("/{utilisateurId}/role/{roleId}")
    public ResponseEntity<Utilisateur> assignRoleToUtilisateur(@PathVariable Long utilisateurId, @PathVariable Long roleId) {
        Utilisateur utilisateur = utilisateurService.assignRoleToUtilisateur(utilisateurId, roleId);
        return ResponseEntity.ok(utilisateur);
    }

    // 5) Ajout d'une image à un Utilisateur
    @PostMapping("/{utilisateurId}/image")
    public ResponseEntity<Utilisateur> addImageToUtilisateur(@PathVariable Long utilisateurId, @RequestBody UtilisateurImage image) {
        Utilisateur utilisateur = utilisateurService.ajouterImage(utilisateurId, image.getCheminImage(), image.getNomImage());
        return ResponseEntity.ok(utilisateur);
    }

    // 6) Suppression d'un Utilisateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    // 7) Suppression d'un Rôle par ID
    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        utilisateurService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

    // 8) Suppression d'une image d'un Utilisateur
    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long utilisateurId, @PathVariable Long imageId) {
        utilisateurService.deleteImage(utilisateurId, imageId);
        return ResponseEntity.noContent().build();
    }
}
