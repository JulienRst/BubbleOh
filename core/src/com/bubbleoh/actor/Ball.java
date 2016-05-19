package com.bubbleoh.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Julien on 19/05/2016.
 */
public class Ball extends Actor {

    private final float speed;
    private float x = 420;
    private float y = 200;

    private float vX;
    private float vY;
    private float angle;

    private ShapeRenderer shapeRenderer;
    protected Body body;

    public Ball(Body body, float speed , float vX , float vY , float angle) {
        this.speed = speed;
        this.vX = vX;
        this.vY = vY;
        this.angle = angle;
        shapeRenderer = new ShapeRenderer();
        this.vX = (float)(speed * Math.cos(Math.toRadians(angle)));
        this.vY = (float)(-speed * (float)Math.sin(Math.toRadians(angle)));
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
        float ballMinX = 0+20+5;//+radius
        float ballMinY = 0+20+5;
        float ballMaxX = 840-20-5;
        float ballMaxY = 400-20-5;

        this.x += this.vX;
        this.y += this.vY;

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
