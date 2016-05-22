package com.bubbleoh.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubbleoh.Config;


/**
 * Created by Julien on 19/05/2016.
 *
 * La Balle hérite de la classe Actor (c'est un dire que c'est un élément de notre scène
 */
public class Ball extends Actor {

    private float x ;
    private float y ;

    private float vX;
    private float vY;


    private ShapeRenderer shapeRenderer;
    protected Body body;

    /**
     *
     * @param body , contexte
     * @param px , position en X
     * @param py , position en Y
     * @param vX , vitesse en X
     * @param vY , vitesse en Y
     */

    public Ball(Body body, float px,float py, float vX , float vY) {
        this.x = px;
        this.y = py;
        this.vX = vX;
        this.vY = vY;
        shapeRenderer = new ShapeRenderer();
        this.body = body;

    }

    public float getX(){ return x; }
    public float getY(){ return y; }

    public void setX(float x){ this.x = x; }
    public void setY(float y){ this.y = y; }

    public float getVX(){ return this.vX;}
    public float getVY(){ return this.vY;}

    public void setVX(float vx){
        this.vX = vx;
    }
    public void setVY(float vy){
        this.vY = vy;
    }

    public void stop(){
        this.vX = 0;
        this.vY = 0;
    }

    /**
     * Décrit le comportement a adopter pour dessiner notre objet
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // Forme remplie
        shapeRenderer.setColor(0, 0, 0, 1); // Couleur noir
        shapeRenderer.circle(x, y, Config.BALL_RADIUS); // Cercle
        shapeRenderer.end();
        batch.begin();
    }

    /**
     * Décrit le comportement a adopter pour chaque changement de frame (vitesse, collision, etc...)
     * @param delta
     */
    @Override
    public void act(float delta){
        super.act(delta);
        //Virtualisation de la Boîte
        float ballMinX = Config.BALL_RADIUS +5;
        float ballMinY = Config.BALL_RADIUS +5;
        float ballMaxX = Config.WINDOW_WIDTH - Config.BALL_RADIUS -5;
        float ballMaxY = Config.WINDOW_HEIGHT - Config.BALL_RADIUS -5;

        //On change les coordonnées en fonction de la vitesse
        this.x += this.vX;
        this.y += this.vY;

        //Détection des collisions avec la boîte en X
        if (x < ballMinX) {
            vX = -vX;
            x = ballMinX;
            AudioUtils.getInstance().playJumpSound();

        } else if (x > ballMaxX) {
            vX = -vX;
            x = ballMaxX;
            AudioUtils.getInstance().playJumpSound();

        }
        //Détection des collisions avec la boîte en Y
        if (y < ballMinY) {
            vY = -vY;
            y = ballMinY;
            AudioUtils.getInstance().playJumpSound();

        } else if (y > ballMaxY) {
            vY = -vY;
            y = ballMaxY;
            AudioUtils.getInstance().playJumpSound();

        }
    }

}
