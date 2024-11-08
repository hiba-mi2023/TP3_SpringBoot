package com.TP3.entities;


import jakarta.persistence.*;

import com.TP3.entities.Role;

@Entity
public class Utilisateur {

    @Id
    private Long id;
    private String nom;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UtilisateurImage utilisateurImage;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UtilisateurImage getUtilisateurImage() {
        return utilisateurImage;
    }

    public void setUtilisateurImage(UtilisateurImage utilisateurImage) {
        this.utilisateurImage = utilisateurImage;
    }
}
