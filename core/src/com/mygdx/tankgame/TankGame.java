package com.mygdx.tankgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tankgame.templates.*;

public class TankGame extends Game {
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public SpriteBatch batch;

//	Implemented Singleton designPattern

	public static TankGame tankGame = null;
	public static TankGame getInstance(){
		if (tankGame == null) {
			tankGame = new TankGame();
		}
		return tankGame;
	}
	private TankGame(){

	}
	@Override
	public void create() {
		batch = new SpriteBatch();

//		this.setScreen(new PlayGame(this, new ClassGame(new Player(1, 1), new Player(2, 3))));
//		this.setScreen(new GameOverScreen(this, 2));
		this.setScreen(new FirstMenuScreen((TankGame.getInstance())));

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
