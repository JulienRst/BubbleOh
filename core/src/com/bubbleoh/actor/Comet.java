package com.bubbleoh.actor;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Julien on 20/05/2016.
 *
 * Hérite de la classe obstacle
 * On rédifinit ici juste le constructeur et la fonction act
 */
public class Comet extends Obstacle {

    float r2;
    float origX;
    float origY;
    float accum = 0;

    /**
     *
     * @param body , contexte
     * @param x , position en X
     * @param y , position en Y
     * @param r , rayon de la comète
     * @param r2 , distance entre le centre de son mouvement et elle
     */
    public Comet(Body body, float x, float y, float r, float r2){
        super(body,x,y,0.760f,0.804f,0.384f,r);
        this.r2 = r2;
        origX = x;
        origY = y;
    }

    /**
     * On récupère l'écart entre chaque frame et on l'accumule dans une variable qu'on utilise pour faire un mouvement de cercle
     * avec Sinus et Cosinus
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        accum += delta;
        this.x = (float)Math.cos(accum)*r2 + origX;
        this.y = (float)Math.sin(accum)*r2 + origY;
    }

}
