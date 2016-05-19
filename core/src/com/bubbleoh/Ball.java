package com.bubbleoh;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Julien on 19/05/2016.
 */
public class Ball extends Actor {

    private int x = 0;
    private int y = 0;

    private int vX = 5;
    private int vY = 5;

    private ShapeRenderer shapeRenderer;
    protected Body body;

    public Ball(Body body) {
        shapeRenderer = new ShapeRenderer();
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
        this.x += this.vX;
        this.y += this.vY;
    }

}
