

package com.bubbleoh;

import com.badlogic.gdx.Game;
import com.bubbleoh.screens.GameScreen;

public class BubbleOh extends Game {

	public BubbleOh() {

	}

	@Override
	public void create() {
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {

	}
}
