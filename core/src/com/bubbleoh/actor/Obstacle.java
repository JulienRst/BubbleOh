package com.bubbleoh.actor;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Un obstacle est un élément fixe dans la scène qui fait rebondir la balle s'ils entrent en contact
 */
public class Obstacle extends Actor {

    protected float x;
    protected float y;
    private float radius;
    private Vector3 color;

    private ShapeRenderer shapeRenderer;
    protected Body body;

    public Obstacle(Body body, float px, float py, float r, float g, float b, float radius) {
        this.x = px;
        this.y = py;
        this.color = new Vector3(r,g,b);
        this.radius = radius;

        shapeRenderer = new ShapeRenderer();
        this.body = body;
        body.setUserData(this);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getRadius(){
        return radius;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color.x,color.y,color.z,1);
        shapeRenderer.circle(x, y, radius);
        shapeRenderer.end();
        batch.begin();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }
}