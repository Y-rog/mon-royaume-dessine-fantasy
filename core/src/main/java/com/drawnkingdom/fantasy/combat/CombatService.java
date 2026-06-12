package com.drawnkingdom.fantasy.combat;

import com.drawnkingdom.fantasy.model.Attaque;
import com.drawnkingdom.fantasy.model.Heros;
import com.drawnkingdom.fantasy.model.Monstre;
import com.drawnkingdom.fantasy.model.Personnage;

/**
 * Contient la logique métier du combat : calcul des dégâts, application
 * des effets sur les personnages, et détection de fin de combat.
 * Cette classe est sans état (stateless) — tous les personnages sont
 * passés en paramètre de chaque méthode.
 */
public class CombatService {

    /**
     * Effectue une attaque normale d'un personnage sur un autre.
     * Les dégâts infligés sont au minimum 1, même si la défense de la cible
     * est supérieure ou égale à l'attaque.
     *
     * @param attaquant le personnage qui attaque
     * @param cible     le personnage qui subit l'attaque
     * @return le nombre de dégâts infligés
     */
    public int attaquerNormal(Personnage attaquant, Personnage cible) {
        int degats = Math.max(1, attaquant.getAttaque() - cible.getDefense());
        cible.subirDegats(degats);
        return degats;
    }

    /**
     * Effectue une attaque spéciale d'un héros sur un personnage, en ajoutant
     * le bonus de dégâts de l'attaque utilisée. Les dégâts infligés sont
     * au minimum 1.
     *
     * @param attaquant le héros qui attaque
     * @param cible     le personnage qui subit l'attaque
     * @param attaque   l'attaque spéciale utilisée
     * @return le nombre de dégâts infligés
     */
    public int attaquerSpecial(Heros attaquant, Personnage cible, Attaque attaque) {
        int degats = Math.max(1, attaquant.getAttaque() + attaque.degatsBonus() - cible.getDefense());
        cible.subirDegats(degats);
        return degats;
    }

    /**
     * Effectue le tour d'un monstre : il attaque le héros avec une attaque normale.
     *
     * @param monstre le monstre qui attaque
     * @param heros   le héros qui subit l'attaque
     * @return le nombre de dégâts infligés
     */
    public int attaqueMonstre(Monstre monstre, Heros heros) {
        return attaquerNormal(monstre, heros);
    }

    /**
     * Vérifie si le combat est terminé, c'est-à-dire si l'un des deux
     * personnages est vaincu.
     *
     * @param attaquant le premier personnage (généralement le héros)
     * @param cible     le second personnage (généralement le monstre)
     * @return true si l'un des deux personnages est vaincu
     */
    public boolean estCombatTermine(Personnage attaquant, Personnage cible) {
        return attaquant.estVaincu() || cible.estVaincu();
    }

    /**
     * Joue un tour complet de combat : exécute l'attaque du héros (normale ou
     * spéciale, via {@code actionAttaque}), puis, si le combat n'est pas terminé,
     * fait jouer le monstre.
     *
     * @param heros         le héros qui combat
     * @param monstre       le monstre adverse
     * @param actionAttaque l'action d'attaque du héros à exécuter
     *                       (typiquement {@code () -> attaquerNormal(heros, monstre)}
     *                       ou {@code () -> attaquerSpecial(heros, monstre, attaque)})
     * @return le résultat du tour : si le combat est terminé, et qui a gagné
     */
    public ResultatTour jouerTour(Heros heros, Monstre monstre, Runnable actionAttaque) {
        // 1. le héros attaque (normale ou spéciale, selon l'action fournie)
        actionAttaque.run();

        // 2. le héros a-t-il vaincu le monstre ?
        if (estCombatTermine(heros, monstre)) {
            return new ResultatTour(true, monstre.estVaincu());
        }

        // 3. sinon, le monstre attaque à son tour
        attaqueMonstre(monstre, heros);

        // 4. le monstre a-t-il vaincu le héros ?
        if (estCombatTermine(heros, monstre)) {
            return new ResultatTour(true, monstre.estVaincu());
        }

        // 5. combat toujours en cours
        return new ResultatTour(false, false);
    }
}
