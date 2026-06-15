package com.drawnkingdom.fantasy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.drawnkingdom.fantasy.model.Heros;
import com.drawnkingdom.fantasy.model.TypeCase;

/**
 * Écran de la carte (overworld). Affiche une grille de cases (herbe, arbres,
 * monstre) ainsi que le héros, déplaçable au clavier avec les flèches
 * directionnelles. Le héros ne peut pas traverser les cases de type MUR.
 */
public class CarteScreen implements Screen {

    /** Hauteur de la carte, en nombre de cases. */
    private static final int HAUTEUR = 12;

    /** Largeur de la carte, en nombre de cases. */
    private static final int LARGEUR = 16;

    /** Taille d'une case de la grille, en pixels. */
    private static final int TAILLE_CASE = 40;

    /** Délai minimum (en secondes) entre deux déplacements du héros. */
    private static final float DELAI_DEPLACEMENT = 0.15f;

    /** Taille du sprite du héros, en pixels (peut être plus grand que la case). */
    private static final int TAILLE_SPRITE_HEROS = 56;

    /** Taille du sprite du monstre, en pixels (peut être plus grand que la case). */
    private static final int TAILLE_SPRITE_MONSTRE = 56;

    /** Position de départ du héros sur la grille (colonne). */
    private static final int HEROS_X_DEPART = 1;

    /** Position de départ du héros sur la grille (ligne). */
    private static final int HEROS_Y_DEPART = 1;

    /** Grille de la carte : pour chaque case, son type (HERBE, MUR, MONSTRE). */
    private TypeCase[][] grille;

    /** Outil de dessin des sprites (images), créé une fois et réutilisé chaque frame. */
    private SpriteBatch batch;

    /** Sprite du héros. */
    private Texture spriteHeros;

    /** Tuile de fond pour les cases HERBE. */
    private Texture tuileHerbe;

    /** Tuile de fond pour les cases MUR (ici : un arbre). */
    private Texture tuileMur;

    /** Sprite du monstre (Dragon), affiché sur la case MONSTRE. */
    private Texture spriteMonstre;

    /** Position du héros sur la grille (colonne). */
    private int heroX;

    /** Position du héros sur la grille (ligne). */
    private int heroY;

    /** Temps écoulé (en secondes) depuis le dernier déplacement du héros. */
    private float tempsDepuisDeplacement = 0f;

    private final Heros heros;

    public CarteScreen(Heros heros) {
        this.heros = heros;
    }

    /**
     * Initialise l'écran : crée la grille, charge les sprites/tuiles,
     * et place le héros à sa position de départ.
     */
    @Override
    public void show() {
        this.grille = creerGrille();
        this.batch = new SpriteBatch();

        // chargement des sprites et tuiles depuis le dossier assets/
        this.spriteHeros = new Texture(heros.getImagePath());
        this.spriteMonstre = new Texture("images/personnages/monstres/Dragon-Idle.png");
        this.tuileHerbe = new Texture("images/tuiles/tuile_herbe.png");
        this.tuileMur = new Texture("images/tuiles/tuile_arbre.png");

        // position de depart du heros : juste a l'interieur du coin (case 1,1)
        this.heroX = HEROS_X_DEPART;
        this.heroY = HEROS_Y_DEPART;
    }

    /**
     * Gère le déplacement du héros, puis dessine la grille et les sprites.
     *
     * @param delta temps écoulé depuis la frame précédente, en secondes
     */
    @Override
    public void render(float delta) {

        // met a jour la position du heros selon les touches pressees
        gererDeplacement(delta);

        // efface l'ecran entier (couleur de fond, recouverte par les tuiles ensuite)
        ScreenUtils.clear(0f, 0f, 0f, 1f);

        // signale au SpriteBatch : "je vais dessiner des images"
        batch.begin();

        // parcourt chaque case de la grille : y = ligne, x = colonne
        for (int y = 0; y < HAUTEUR; y++) {
            for (int x = 0; x < LARGEUR; x++) {
                TypeCase type = grille[y][x];

                // selon le type de la case, dessine la bonne tuile/sprite
                // a la position pixel (x * TAILLE_CASE, y * TAILLE_CASE)
                switch (type) {
                    case MUR -> batch.draw(tuileMur, x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                    case HERBE -> batch.draw(tuileHerbe, x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                    case MONSTRE -> {
                        // dessine d'abord l'herbe (le sol), PUIS le monstre par-dessus
                        // (sinon le fond transparent du monstre laisserait voir le noir de clear())
                        batch.draw(tuileHerbe, x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                        batch.draw(spriteMonstre, x * TAILLE_CASE, y * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
                    }
                }
            }
        }

        // dessine le sprite du heros, centre sur sa case
        // (si TAILLE_SPRITE_HEROS > TAILLE_CASE, le decalage est negatif :
        //  le sprite depasse symetriquement de chaque cote de la case)
        int decalageHeros = (TAILLE_CASE - TAILLE_SPRITE_HEROS) / 2;
        batch.draw(spriteHeros,
            heroX * TAILLE_CASE + decalageHeros,
            heroY * TAILLE_CASE + decalageHeros,
            TAILLE_SPRITE_HEROS, TAILLE_SPRITE_HEROS);

        // signale au SpriteBatch : "j'ai fini de dessiner pour cette frame"
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    /**
     * Libère les textures et le SpriteBatch chargés par cet écran.
     */
    @Override
    public void dispose() {
        batch.dispose();
        spriteHeros.dispose();
        spriteMonstre.dispose();
        tuileHerbe.dispose();
        tuileMur.dispose();
    }

    /**
     * Crée la grille de la carte : des arbres (MUR) sur les 4 bords,
     * un monstre au centre, et de l'herbe partout ailleurs.
     *
     * @return la grille nouvellement créée
     */
    TypeCase[][] creerGrille() {

        TypeCase[][] nouvelleGrille = new TypeCase[HAUTEUR][LARGEUR];

        // parcourt chaque case (y = ligne, x = colonne)
        for (int y = 0; y < HAUTEUR; y++) {
            for (int x = 0; x < LARGEUR; x++) {

                // les 4 bords de la grille sont des MUR (arbres)
                if (y == 0 || x == 0 || y == HAUTEUR - 1 || x == LARGEUR - 1) {
                    nouvelleGrille[y][x] = TypeCase.MUR;
                } else {
                    // la case au centre de la grille contient le monstre
                    if (y == HAUTEUR / 2 && x == LARGEUR / 2) {
                        nouvelleGrille[y][x] = TypeCase.MONSTRE;
                    } else {
                        // toutes les autres cases sont de l'herbe
                        nouvelleGrille[y][x] = TypeCase.HERBE;
                    }
                }
            }
        }

        return nouvelleGrille;
    }

    /**
     * Vérifie les touches directionnelles et déplace le héros si possible.
     * Le déplacement n'a lieu que toutes les {@link #DELAI_DEPLACEMENT} secondes,
     * même si une touche est maintenue, pour garder un rythme régulier.
     * Le héros ne peut pas se déplacer sur une case de type MUR.
     *
     * @param delta temps écoulé depuis la frame précédente, en secondes
     */
    private void gererDeplacement(float delta) {

        // accumule le temps ecoule depuis le dernier deplacement
        tempsDepuisDeplacement += delta;

        // si le delai minimum n'est pas encore atteint, on ne fait rien cette frame
        if (tempsDepuisDeplacement < DELAI_DEPLACEMENT) {
            return;
        }

        // le delai est atteint : on remet le compteur a zero pour le prochain deplacement
        tempsDepuisDeplacement = 0f;

        // calcule la position FUTURE du heros, sans encore modifier sa position actuelle
        int nouveauX = heroX;
        int nouveauY = heroY;

        // verifie chaque touche directionnelle MAINTENUE (isKeyPressed = en continu)
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            nouveauX++;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            nouveauX--;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            nouveauY++;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            nouveauY--;
        }

        // applique le deplacement SEULEMENT si la case de destination n'est pas un MUR
        if (grille[nouveauY][nouveauX] != TypeCase.MUR) {
            heroX = nouveauX;
            heroY = nouveauY;
        }
    }
}
