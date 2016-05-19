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
 *
 * GameStage va nous permettre de définir les différents acteurs présent sur la scène
 * Mais également de parametrer la caméra
 *
 * @see com.badlogic.gdx.scenes.scene2d.Stage
 */
public class GameStage extends Stage {

    private OrthographicCamera camera;
    private Ball ball;
    private Box box;
    private World world;

    /**
     * C'est le constructeur de notre écran
     * On va créer : notre caméra, notre balle principale ainsi que notre boîte
     */

    public GameStage(){
        super(new ScalingViewport(Scaling.stretch, 840, 400, new OrthographicCamera(840, 400)));
        createWorld();
        setUpCamera();
        setUpBall();
        setUpBox();
    }

    /**
     * @return void
     *
     * On crée notre monde avec la gravité (voir la doc libgdx pour plus d'informations)
     *
     * @see com.badlogic.gdx.physics.box2d.World
     */

    private void createWorld(){
       world = new World(new Vector2(0, -10),true);
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

    /**
     * On crée la définition du corps de notre Balle
     * Objet qui va bouger, qu'il appartient au monde crée
     *
     * On construit notre balle et on l'ajoute aux acteurs.
     */

    private void setUpBall(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(0,0));
        Body body = world.createBody(bodyDef);

        //TODO :: Implement Extractor on this
        float speed = 2;
        float vX = 2;
        float vY = 10;
        float angle = 0; //10;
        ball = new Ball( body,  speed ,  vX ,  vY ,  angle);
        addActor(ball);
    }

    /**
     * On crée la définition du corps de notre Boîte
     * Objet qui va rester immobile, qu'il appartient au monde crée
     *
     * On construit notre boîte et on l'ajoute aux acteurs.
     */

    private void setUpBox(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0,0));
        Body body = world.createBody(bodyDef);
        box = new Box(body);
        addActor(box);
    }
}
