package com.drawnkingdom.fantasy.model;

/**
 * Représente un personnage du jeu (héros ou monstre).
 * Contient les attributs et comportements communs à tous les personnages :
 * points de vie, statistiques de combat, et sprite associé.
 */
public abstract class Personnage {
    private String nom;
    private int pv;
    private int pvMax;
    private int attaque;
    private int defense;
    private String imagePath;

    /**
     * Crée un nouveau personnage. Les points de vie actuels sont initialisés au maximum.
     *
     * @param nom       le nom du personnage
     * @param pvMax     les points de vie maximum
     * @param attaque   la puissance d'attaque de base
     * @param defense   la réduction de dégâts subis
     * @param imagePath le chemin vers le sprite du personnage (depuis resources)
     */
    public Personnage(String nom, int pvMax, int attaque, int defense, String imagePath) {
        this.nom = nom;
        this.pv = pvMax;
        this.pvMax = pvMax;
        this.attaque = attaque;
        this.defense = defense;
        this.imagePath = imagePath;
    }

    /** @return le nom du personnage */
    public String getNom() {
        return this.nom;
    }

    /** @return les points de vie actuels */
    public int getPv() {
        return this.pv;
    }

    /** @return les points de vie maximum */
    public int getPvMax() {
        return this.pvMax;
    }

    /** @return la puissance d'attaque de base */
    public int getAttaque() {
        return this.attaque;
    }

    /** @return la réduction de dégâts subis */
    public int getDefense() {
        return this.defense;
    }

    /**
     * Réduit les points de vie du personnage. Les PV ne descendent jamais sous 0.
     *
     * @param degats le nombre de dégâts à infliger
     */
    public void subirDegats(int degats) {
        this.pv = Math.max(0, this.pv - degats);
    }

    /**
     * Augmente les points de vie du personnage. Les PV ne dépassent jamais pvMax.
     *
     * @param soin le nombre de points de vie à restaurer
     */
    public void soigner(int soin) {
        this.pv = Math.min(this.pvMax, this.pv + soin);
    }

    /** @return true si le personnage n'a plus de points de vie (pv <= 0) */
    public boolean estVaincu() {
        return this.pv <= 0;
    }

    /** @return le chemin vers le sprite du personnage (depuis resources) */
    public String getImagePath() {
        return this.imagePath;
    }
}
