package com.drawnkingdom.fantasy;

import com.badlogic.gdx.Game;
import com.drawnkingdom.fantasy.screens.HeroScreen;

/**
 * Point d'entrée du jeu. Gère l'écran actif.
 */
public class MonRoyaumeGame extends Game {

    @Override
    public void create() {
        setScreen(new HeroScreen());
    }
}
