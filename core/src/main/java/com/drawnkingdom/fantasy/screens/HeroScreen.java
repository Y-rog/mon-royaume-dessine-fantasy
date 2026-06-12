package com.drawnkingdom.fantasy.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Écran de test affichant le sprite d'un héros au centre de l'écran.
 */
public class HeroScreen implements Screen {

    private SpriteBatch batch;
    private Texture heroTexture;

    @Override
    public void show() {
        batch = new SpriteBatch();
        heroTexture = new Texture("images/personnages/heros/Chevalier-Idle.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(heroTexture, 100, 100);
        batch.end();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        heroTexture.dispose();
    }
}
