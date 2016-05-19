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

    /**
     * On initialise le shape Renderer (qui va nous permettre de dessiner la forme que l'on veut)
     *
     * @param body , définition du corps de notre objet généré dans GameStage
     */
    public Box(Body body) {
        shapeRenderer = new ShapeRenderer();
        this.body = body;
    }

    /**
     * batch c'est le moteur de rendu a qui on va dire ce qu'il doit dessiner
     * parentAlpha c'est utilisé par la super.draw()
     *
     * @param batch
     * @param parentAlpha
     */
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

    /**
     * On a pas besoin d'update la box à chaque frame, d'où le vide de cette fonction
     * @param delta
     */
    @Override
    public void act(float delta){
        super.act(delta);
    }

}
