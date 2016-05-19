package com.bubbleoh.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.bubbleoh.actor.Ball;
import com.bubbleoh.actor.Box;

/**
 * Created by Julien on 19/05/2016.
 */
public class GameStage extends Stage {

    private OrthographicCamera camera;
    private Ball ball;
    private Box box;

    public GameStage(){
        super(new ScalingViewport(Scaling.stretch, 840, 400, new OrthographicCamera(840, 400)));
        setUpCamera();
        setUpBall();
        setUpBox();
    }

    /**
     * @return void
     *
     * On crée une caméra othornormée avec les dimensions de la fenêtre
     * On place la position de la caméra au milieu de notre fenêtre
     * On update les coordonnées de la caméra.
     */

    private void setUpCamera() {
        camera = new OrthographicCamera(840, 400);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setUpBall(){
        World world = new World(new Vector2(0, -10),true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(0,0));
        Body body = world.createBody(bodyDef);
        ball = new Ball(body);
        addActor(ball);
    }

    private void setUpBox(){
        World world = new World(new Vector2(0, -10),true);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0,0));
        Body body = world.createBody(bodyDef);
        box = new Box(body);
        addActor(box);
    }
}
