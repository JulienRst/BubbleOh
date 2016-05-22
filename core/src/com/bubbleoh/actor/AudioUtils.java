package com.bubbleoh.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioUtils {

    private static AudioUtils ourInstance = new AudioUtils();
    private static Music music;
    private static Sound jumpSound;
    private static Sound hitSound;




    /**
     * On déclare le constructeur en privé pour qu'il ne soit pas utilisé en dehors l'utilisation static
     */
    private AudioUtils() {
    }

    /**
     * Renvoie l'instance du Singleton
     * @return l'instance
     */

    public static AudioUtils getInstance() {
        return ourInstance;
    }


    /**
     * initialisation de la musique
     */

    public void init() {
        music = Gdx.audio.newMusic(Gdx.files.internal("fun_in_a_bottle.mp3"));
        music.setLooping(true);
        playMusic();
        jumpSound = createSound("jump.wav");
        hitSound = createSound("hit.wav") ;
    }

    /**
     * Permet de créer un fichier Sound afin de pouvoir le lire
     * @param soundFileName
     * @return
     */
    public Sound createSound(String soundFileName) {
        return Gdx.audio.newSound(Gdx.files.internal(soundFileName));
    }

    /**
     * Permet de lire la musique du fond
     */
    public void playMusic() {
            music.play();
    }

    /**
     * Permet de jouer le son attribué aux collisions
     */
    public void playJumpSound() {
        jumpSound.play();
    }


    /**
     * Permet de jouer le son attribué aux lancement de la balle
     */
    public void playHitSound() {
        hitSound.play();
    }

}
