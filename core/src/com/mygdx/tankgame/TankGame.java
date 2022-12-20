package com.mygdx.tankgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tankgame.templates.*;

public class TankGame extends Game {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
//		this.setScreen(new PlayGame(this, new ClassGame());
		this.setScreen(new FirstMenuScreen((this)));
	}

	@Override
	// render() is called at the beginning of each deltaTime interval
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		super.dispose();
	}
}
