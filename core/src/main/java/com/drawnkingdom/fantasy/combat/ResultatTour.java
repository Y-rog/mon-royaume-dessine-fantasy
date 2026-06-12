package com.drawnkingdom.fantasy.combat;

/**
 * Résultat d'un tour de combat.
 *
 * @param termine  true si le combat est terminé après ce tour
 * @param victoire true si le héros a gagné (pertinent seulement si termine == true)
 */
public record ResultatTour(boolean termine, boolean victoire) {}