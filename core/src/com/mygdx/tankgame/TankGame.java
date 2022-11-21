package com.mygdx.tankgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.templates.MainGameScreen;

public class TankGame extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainGameScreen(this));
	}

	@Override
	// render() is called at the beginning of each deltaTime interval
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
}
