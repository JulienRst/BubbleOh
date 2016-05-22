package com.bubbleoh.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubbleoh.Config;

import java.util.HashMap;

/**
 * Défini le fond d'écran qui défile
 */
public class Background extends Actor {

    private final TextureRegion textureRegion ;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;

    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();


    private int speed = 100;

    /**
     * Le constructeur qui récupère la texture du fond d'écran
     * puis créée deux rectangle pour le faire faire défiler
     */
    public Background() {
        texturesMap.put("background", new TextureRegion(new Texture(Gdx.files.internal("background.png"))));
        textureRegion = texturesMap.get("background");
        textureRegionBounds1 = new Rectangle( 0, 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        textureRegionBounds2 = new Rectangle(Config.WINDOW_WIDTH -2 , 0, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    }

    /**
     * Permet de mettre à jour toute les fonctions
     * @param delta
     */
    @Override
    public void act(float delta) {

        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }


    /**
     * Permets de dessiner le fond d'écrand sur la fenêtre
     * on dessine le premier rectangle puis le deuxième pour faire défiler le fond d'écran
     * @param batch
     * @param parentAlpha
     */

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y,Config.WINDOW_WIDTH,
                Config.WINDOW_HEIGHT);
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Config.WINDOW_WIDTH,
                Config.WINDOW_HEIGHT);
    }

    /**
     * Permet de vérifier si on est arrivé à la fin de l'image du fond d'écran
     * @param delta
     * @return
     */
    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - (delta * speed)) <= 0;
    }

    /**
     * Permet de mettre à jour la coordonnée X des deux background avec un paramètre de vitesse
     * @param delta
     */
    private void updateXBounds(float delta) {
        textureRegionBounds1.x += delta * speed;
        textureRegionBounds2.x += delta * speed;
    }

    /**
     * Permet de mettre à jour les côtés du fond d'écran pourque la fin du premier rectangle devienne le début de l'autre
     */
    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(Config.WINDOW_WIDTH, 0,Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    }

}
