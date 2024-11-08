package com.TP3.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Role {

    @Id
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs;

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

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
