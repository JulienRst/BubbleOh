package com.bubbleoh;

/**
 * Created by Léa on 20/05/2016.
 *
 * Classe contenant toutes les constantes utiles aux jeux, que ce soit pour la génération du terrain que de la balle
 */
public class Config {
    //BASE
    //-Largeur de la fenêtre
    public final static int WINDOW_WIDTH = 1600;
    //-Hauteur de la fenêtre
    public final static int WINDOW_HEIGHT = 900;
    //-Position initial de la balle
    public final static float BALL_X = WINDOW_WIDTH/2;
    public final static float BALL_Y = WINDOW_HEIGHT/2;
    //-Rayon initial de la balle
    public final static float BALL_RADIUS = 20;
    //LES OBSTACLES (x,y,r,g,b,radius)
    public final static float OBSTACLES_COORD[][] = {{210,100,0.855f,0.329f,0.153f,20},{150,400,0.953f,0.816f,0.878f,30},{20,20,0.760f,0.804f,0.384f,15},{1200,800,0.847f,0.722f,0.125f,20}};
    // METEORES (x,y,radius,ellipse)
    public final static float METEORES[][] = {{WINDOW_WIDTH/2,WINDOW_HEIGHT/2,30,300}, {1200,700,20,400}};
}
