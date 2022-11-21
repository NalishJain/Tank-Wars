package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;

public class MainGameScreen implements Screen {
    Texture img;
    TankGame game;
    float x;
    float y;

    public MainGameScreen(TankGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.44f,0.31f,0.96f, 0.5f);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y = y+4;
        } if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y = y - 4;
        } if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x = x - 4;
        } if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x = x + 4;
        }

        game.batch.begin();
        game.batch.draw(img, x, y);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        img.dispose();
    }
}
