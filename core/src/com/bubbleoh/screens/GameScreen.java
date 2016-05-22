package com.bubbleoh.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.bubbleoh.stages.GameStage;

/**
 * Screen c'est la class qui va nous servir de simple moteur
 * Avec le constructeur on dit : cette scène sera dessinée
 * Avec la fonction render, on clear l'espace et on dessine les éléments de la scène
 */
public class GameScreen implements Screen {


    private GameStage stage;

    public GameScreen() {
        stage = new GameStage();
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1,1,1,1);

        //Update the screen
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
