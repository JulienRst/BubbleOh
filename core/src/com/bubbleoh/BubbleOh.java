

package com.bubbleoh;

import com.badlogic.gdx.Game;
import com.bubbleoh.screens.GameScreen;

/**
 * Classe principale :
 * Appelle l'écran du Jeu
 *
 */
public class BubbleOh extends Game {

	public BubbleOh() {

	}

	/**
	 * Juste met défini l'écran du jeu à celui créé :
	 * Regardez bubbleoh.screens.GameScreen.java pour voir ce qu'il fait
	 */

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
