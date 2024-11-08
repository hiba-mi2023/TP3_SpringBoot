package com.TP3.service;
import com.TP3.entities.Role;
import com.TP3.entities.Utilisateur;
import com.TP3.entities.UtilisateurImage;
import com.TP3.repository.RoleRepository;
import com.TP3.repository.UtilisateurImageRepository;
import com.TP3.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurImageRepository utilisateurImageRepository;

    // 1) Création d'un nouvel Utilisateur avec son rôle associé
    public Utilisateur creerUtilisateur(String email, String nom, String roleNom) {
        Optional<Role> role = roleRepository.findByNom(roleNom);
        if (role.isPresent()) {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setEmail(email);
            utilisateur.setNom(nom);
            utilisateur.setRole(role.get());
            return utilisateurRepository.save(utilisateur);
        } else {
            throw new RuntimeException("Rôle non trouvé");
        }
    }

    // 2) Ajout d'une image à un Utilisateur existant
    public Utilisateur ajouterImage(Long utilisateurId, String cheminImage, String nomImage) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        UtilisateurImage image = new UtilisateurImage();
        image.setCheminImage(cheminImage);
        image.setNomImage(nomImage);
        image.setUtilisateur(utilisateur);

        utilisateurImageRepository.save(image);
        utilisateur.setUtilisateurImage(image);

        return utilisateurRepository.save(utilisateur);
    }

    // 3) Récupérer les Utilisateurs ayant un rôle spécifique
    public List<Utilisateur> getUtilisateursByRole(String roleNom) {
        Role role = roleRepository.findByNom(roleNom)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        return utilisateurRepository.findByRole(role);
    }

    // 4) Récupérer tous les Utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // 5) Récupérer un Utilisateur par ID
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    // 6) Associer un rôle à un Utilisateur
    public Utilisateur assignRoleToUtilisateur(Long utilisateurId, Long roleId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }

    // 7) Supprimer un Utilisateur par ID
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        utilisateurRepository.delete(utilisateur);
    }

    // 8) Supprimer un Rôle par ID
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        roleRepository.delete(role);
    }

    // 9) Supprimer une image d'un Utilisateur
    public void deleteImage(Long utilisateurId, Long imageId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        UtilisateurImage image = utilisateurImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image non trouvée"));

        utilisateurImageRepository.delete(image);
    }
}
