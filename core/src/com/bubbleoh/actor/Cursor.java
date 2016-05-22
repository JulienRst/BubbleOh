package com.bubbleoh.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bubbleoh.Config;

/**
 * Created by Julien on 20/05/2016.
 *
 * Permet de lancer la Balle
 */
public class Cursor extends Actor {
    private float x = Config.BALL_X;
    private float y = Config.BALL_Y;

    private float origX;
    private float origY;
    private float startX;
    private float startY;
    private float currentX;
    private float currentY;

    public boolean activ = false;

    private ShapeRenderer shapeRenderer;
    protected Body body;

    public Cursor(Body body){
        shapeRenderer = new ShapeRenderer();
        this.body = body;
    }

    public void setActiv(boolean activ){
        this.activ = activ;
    }

    public void setStart(float x, float y){
        this.startX = x;
        this.startY = y;
    }

    public void setOrig(float x, float y){
        this.origX = x;
        this.origY = y;
    }

    public void processEnd(){
        this.x = 0;
        this.y = 0;
        this.origX = 0;
        this.origY = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!activ)
            return;
        super.draw(batch, parentAlpha);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor((float)0.5, (float)0.5, (float)0.5, 1);
        shapeRenderer.circle(x, y, 20);
        shapeRenderer.end();
        batch.begin();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if(activ){
            this.x = this.startX + Gdx.input.getX() - this.origX;
            this.y = this.startY - Gdx.input.getY() + this.origY;
        }
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getStartX(){ return startX; }
    public float getStartY(){ return startY; }
}
