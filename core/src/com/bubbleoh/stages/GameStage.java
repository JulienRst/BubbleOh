package com.bubbleoh.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.bubbleoh.Config;
import com.bubbleoh.actor.*;

import java.util.ArrayList;

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
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    private ArrayList<Comet> cometes = new ArrayList<Comet>();
    private Cursor cursor;
    private World world;

    /**
     * C'est le constructeur de notre écran
     * On va créer : notre caméra, notre balle principale ainsi que notre boîte
     * Background / Les Obstacles / Les comètes
     */

    public GameStage(){
        super(new ScalingViewport(Scaling.stretch, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, new OrthographicCamera(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT)));
        Gdx.input.setInputProcessor(this);
        createWorld();
        setUpBackground();
        setUpMusic();
        setUpCamera();
        setUpBall();
        setUpCursor();
        setUpObstacle();
        setUpComete();
        setUpBox();
    }

    /**
     * Replace la balle à son point de départ
     */

    private void reset(){
        ball.setX(Config.BALL_X);
        ball.setY(Config.BALL_Y);
        ball.setVX(0);
        ball.setVY(0);
    }

    /**
     * @return void
     *
     * Crée un monde dans lequel o
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

    private void setUpBackground() {
        addActor(new Background());
    }
    private void setUpCamera() {
        camera = new OrthographicCamera(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    /**
     * Permet d'instancier la classe de la musique
     */
    private void setUpMusic() {

        AudioUtils.getInstance().init();

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

        float vX = 0;
        float vY = 0;

        float px = Config.BALL_X;
        float py =  Config.BALL_Y;
        ball = new Ball(body ,px, py, vX ,  vY);
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

    /**
     * Génère les obstacles à partir du fichier de config, et les ajoutes dans le monde
     */

    private void setUpObstacle() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0, 0));
        Body body2 = world.createBody(bodyDef);

        for(float obstacle[] : Config.OBSTACLES_COORD){
            Obstacle tmp = new Obstacle(body2, obstacle[0], obstacle[1],obstacle[2],obstacle[3],obstacle[4],obstacle[5]);
            obstacles.add(tmp);
        }
        for (Obstacle o : obstacles){
            addActor(o);
        }
    }

    /**
     * Crée les comètes à partir du fichier de config et les ajoutes dans le monde
     */

    private void setUpComete(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(0, 0));
        Body body2 = world.createBody(bodyDef);

        for(float comet[] : Config.METEORES){
            Comet tmp = new Comet(body2, comet[0], comet[1], comet[2], comet[3]);
            cometes.add(tmp);
        }
        for (Comet o : cometes){
            addActor(o);
        }
    }

    /**
     * Crée le curseur et l'ajoute dans le monde
     */

    private void setUpCursor(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(0,0));
        Body body = world.createBody(bodyDef);
        cursor = new Cursor(body);
        addActor(cursor);
    }

    /////////////////////////////////////////
    //  function pour le Lanceur de balle  //
    /////////////////////////////////////////

    /**
     * Fonction de l'événement déclenché lorsque l'on appuie sur le bouton de la souris
     *
     * @param x , coordonnée sur l'axe x
     * @param y , coordonnée sur l'axe y
     * @param pointer
     * @param button
     * @return l'évent de la classe Stage mère
     */
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        ball.stop(); // Arrête la balle
        cursor.setStart(ball.getX(),ball.getY()); //Stock où la balle s'est arrêter
        cursor.setOrig(x,y); //Stock où est-ce que l'on a cliqué
        cursor.setActiv(true); // Autorise le pointer à s'afficher
        return super.touchDown(x, y, pointer, button);
    }

    /**
     * Fonction de l'événement déclenché lorsque l'on relache le bouton de la souris
     *
     * @param x , coordonnée sur l'axe x
     * @param y , coordonnée sur l'axe y
     * @param pointer
     * @param button
     * @return l'évent de la classe mère
     */
    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        processLaunch(); // On lance la balle
        cursor.processEnd(); //On dis au curseur de s'arrêter
        cursor.setActiv(false); //On dis au curseur de ne plus se dessiner
        return super.touchUp(x, y, pointer, button);
    }

    /**
     * On calcule le vecteur vitesse et le donne à la balle
     */
    public void processLaunch(){
        // On récupère le différentiel
        float diffFactor = (float)0.1; //Facteur de ralentissement par rapport à la valeur que l'on récupère
        float diffX = - cursor.getX() + cursor.getStartX(); //Différentiel en X
        float diffY = - cursor.getY() + cursor.getStartY(); //Différentiel en Y
        diffX *= diffFactor; //On multiplie par le différentiel
        diffY *= diffFactor;
        //On fixe la vitesse de la balle
        ball.setVX(diffX);
        ball.setVY(diffY);
    }


    /////////////////////////////////////////
    //              Collision              //
    /////////////////////////////////////////

    /**
     * à chaque frame on active cette fonction, à l'intérieur on va calculer les collisions avec les obstacles et les comètes
     * @param delta, temps écoulé depuis la dernière frame
     */
    @Override
    public void act(float delta){
        super.act(delta);
        //CHECK COLLISION BETWEEN BALL AND OBSTACLE
        //On récupère les coordonnées de la balle
        float bx = ball.getX();
        float by = ball.getY();
        //On crée les variables qui vont servis de container aux coordonnées de l'obstacle en cours
        float ox = 0;
        float oy = 0;
        //On parcourt chaque obstacle du tableau Obstacles
        for (Obstacle o: obstacles) {
            //On récupère les coordonnées de l'obstacle
            ox = o.getX();
            oy = o.getY();
            //On calcule la distance obstacle / balle
            double distance = Math.sqrt((ox - bx)*(ox - bx) + (oy - by)*(oy - by));
            //On regarde s'il y a contact (en prennant compte du rayon de chaque élément)
            if(distance < Config.BALL_RADIUS + o.getRadius()){
                //Si oui on inverse le vecteur vitesse de la balle
                ball.setVX(-ball.getVX());
                ball.setVY(-ball.getVY());
            }
        }
        //CHECK COLLISION WITH COMET
        for (Comet o: cometes) {
            //On récupère les coordonnées de la comète
            ox = o.getX();
            oy = o.getY();
            //Idem que pour les obstacles
            double distance = Math.sqrt((ox - bx)*(ox - bx) + (oy - by)*(oy - by));
            if(distance < Config.BALL_RADIUS + o.getRadius()){
                ball.setVX(-ball.getVX());
                ball.setVY(-ball.getVY());
            }
        }
    }

    /////////////////////////////////////////
    //              RESET                  //
    /////////////////////////////////////////

    /**
     * Fonction qui s'active quand une touche du clavier est enfoncée
     * Va permettre de lancer la fonction RESET
     * @param keyCode , code clavier de la touche appuyée
     * @return
     */
    @Override
    public boolean keyDown(int keyCode){
        //Si la touche appuyé et R on reset
        if (keyCode == 46){
            AudioUtils.getInstance().playHitSound();
            reset();
        }
        return true;
    }
}
