package com.drawnkingdom.fantasy.model;

import java.util.List;

/**
 * Représente le héros contrôlé par le joueur.
 * En plus des attributs communs de {@link Personnage}, le héros dispose
 * d'attaques spéciales et de potions de soin.
 */
public class Heros extends Personnage {

    /** Quantité de points de vie restaurés par une potion. */
    private static final int SOIN_POTION = 30;

    /** Quantité de potions au départ. */
    private static final int POTIONS_DEPART = 3;

    /** Nombre de potions de soin restantes */
    private int nombrePotions;

    /** Liste des attaques spéciales disponibles pour ce héros. */
    private List<Attaque> attaquesSpeciales;

    /**
     * Crée un nouveau héros avec {@value #POTIONS_DEPART} potions de soin.
     *
     * @param nom               le nom du héros
     * @param pvMax             les points de vie maximum
     * @param attaque           la puissance d'attaque de base
     * @param defense           la réduction de dégâts subis
     * @param imagePath         le chemin vers le sprite du héros
     * @param attaquesSpeciales la liste des attaques spéciales du héros
     */
    public Heros(String nom, int pvMax, int attaque, int defense, String imagePath, List<Attaque> attaquesSpeciales) {
        super(nom, pvMax, attaque, defense, imagePath);
        this.attaquesSpeciales = attaquesSpeciales;
        this.nombrePotions = POTIONS_DEPART;
    }

    /** @return le nombre de potions de soin restantes */
    public int getNombrePotions() {
        return this.nombrePotions;
    }

    /** @return la liste des attaques spéciales du héros */
    public List<Attaque> getAttaquesSpeciales() {
        return this.attaquesSpeciales;
    }

    /**
     * Utilise une potion de soin si disponible : restaure {@value #SOIN_POTION} PV
     * (sans dépasser pvMax) et décrémente le nombre de potions.
     *
     * @return le nombre de PV restaurés (0 si aucune potion disponible)
     */
    public int utiliserPotion() {
        if (this.nombrePotions > 0) {
            this.soigner(SOIN_POTION);
            this.nombrePotions--;
            return SOIN_POTION;
        }
        return 0;
    }
}
