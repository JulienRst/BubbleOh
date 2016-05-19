package com.bubbleoh.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;


/**
 * Created by Julien on 19/05/2016.
 */
public class Ball extends Actor {

    private final float speed;
    private float x ;
    private float y ;

    private float vX;
    private float vY;
    private float angle;

    private ShapeRenderer shapeRenderer;
    protected Body body;

    /**
     *
     * @param body , contexte physique
     * @param speed , vitesse
     * @param vX , déplacement en X
     * @param vY , déplacement en Y
     * @param angle , angle de départ
     */

    public Ball(Body body, float speed ,float px,float py, float vX , float vY , float angle) {
        this.speed = speed;
        this.x = px;
        this.y = py;
        this.vX = vX;
        this.vY = vY;
        this.angle = angle;
        shapeRenderer = new ShapeRenderer();
//        this.vX = (float)(speed * Math.cos(Math.toRadians(angle)));
//        this.vY = (float)(-speed * (float)Math.sin(Math.toRadians(angle)));
        this.body = body;

    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.circle(x, y, 20);
        shapeRenderer.end();
        batch.begin();
    }


    @Override
    public void act(float delta){
        super.act(delta);
        boolean estTouche = Gdx.input.isTouched();
        float ballMinX = 0+20+5;//+radius
        float ballMinY = 0+20+5;
        float ballMaxX = 840-20-5;
        float ballMaxY = 400-20-5;

        if(estTouche){
            this.x = Gdx.input.getX();
            this.y = Gdx.input.getY();
        }
            else {
            this.x += this.vX;
            this.y += this.vY;
        }
        if (x < ballMinX) {
            vX = -vX;
            x = ballMinX;
        } else if (x > ballMaxX) {
            vX = -vX;
            x = ballMaxX;
        }

        if (y < ballMinY) {
            vY = -vY;
            y = ballMinY;
        } else if (y > ballMaxY) {
            vY = -vY;
            y = ballMaxY;
        }
    }

}
