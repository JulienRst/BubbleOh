package com.bubbleoh.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Julien on 19/05/2016.
 */

public class Box extends Actor {

    private float x = 5;
    private float y = 5;
    private float w = 830;
    private float h = 390;


    private ShapeRenderer shapeRenderer;
    protected Body body;

    public Box(Body body) {
        shapeRenderer = new ShapeRenderer();
        this.body = body;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(x,y,w,h);
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

}
