package com.drawnkingdom.fantasy.model;

/**
 * Représente un monstre rencontré en combat par le héros.
 * En plus des attributs communs de {@link Personnage}, le monstre possède
 * un niveau qui indique sa difficulté.
 */
public class Monstre extends Personnage {

    /** Niveau de difficulté du monstre. */
    private int niveau;

    /**
     * Crée un nouveau monstre.
     *
     * @param nom       le nom du monstre
     * @param pvMax     les points de vie maximum
     * @param attaque   la puissance d'attaque de base
     * @param defense   la réduction de dégâts subis
     * @param imagePath le chemin vers le sprite du monstre
     * @param niveau    le niveau de difficulté du monstre
     */
    public Monstre(String nom, int pvMax, int attaque, int defense, String imagePath, int niveau) {
        super(nom, pvMax, attaque, defense, imagePath);
        this.niveau = niveau;
    }

    /** @return le niveau de difficulté du monstre */
    public int getNiveau() {
        return this.niveau;
    }
}
