package com.drawnkingdom.fantasy;

import com.badlogic.gdx.Game;
import com.drawnkingdom.fantasy.model.Heros;
import com.drawnkingdom.fantasy.screens.CarteScreen;

import java.util.List;

/**
 * Point d'entrée du jeu. Gère l'écran actif.
 */
public class MonRoyaumeGame extends Game {

    @Override
    public void create() {
        // heros de test, en attendant l'ecran de menu (choix du heros)
        Heros heros = new Heros(
            "Ethan",
            120,
            15,
            10,
            "images/personnages/heros/Chevalier-Idle.png",
            List.of()
        );

        setScreen(new CarteScreen(heros));
    }
}
