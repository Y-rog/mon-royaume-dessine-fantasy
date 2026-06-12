package com.drawnkingdom.fantasy.model;

/**
 * Représente une attaque spéciale qu'un héros peut utiliser en combat.
 *
 * @param nom         le nom de l'attaque (ex: "Coup puissant")
 * @param degatsBonus le bonus de dégâts ajouté à l'attaque de base du héros
 * @param description une courte description de l'attaque
 */
public record Attaque(
        String nom,
        int degatsBonus,
        String description
) {}
