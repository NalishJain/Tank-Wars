package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class FirstMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 350;
    private static final int BUTTON_HEIGHT = 100;

    private static final int LEFT_WIDTH = SCREEN_WIDTH/2;
    private static final int RIGHT_WIDTH = SCREEN_WIDTH/2;
    private static final int BUTTON_RIGHT_LOCATION =  LEFT_WIDTH + (RIGHT_WIDTH/2 - BUTTON_WIDTH/2);

    TankGame game;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture loadButtonActive;
    Texture loadButtonInactive;
    Texture newButtonActive;
    Texture newButtonInactive;
    Texture logo;
    Texture tank;
    Texture bg;
    Texture terrain;
    Texture tank2;
    Texture kPlane;
    Texture terrain_texture;
    Texture parachute;
    TextureRegion plane;
    Texture crate;
    Texture settings;

    public FirstMenuScreen(TankGame game){
        this.game = game;
        exitButtonActive = new Texture("Group 32x.png");
        exitButtonInactive = new Texture("ExitButton.png");
        loadButtonActive = new Texture("Group 22x.png");
        loadButtonInactive = new Texture("LoadGameButton.png");
        newButtonActive = new Texture("Group 12x.png");
        newButtonInactive = new Texture("nButton.png");
        logo = new Texture("Logo.png");
        tank = new Texture("Tank3.png");
        bg = new Texture("bg2.png");
        terrain = new Texture("terrain.png");
        tank2 = new Texture("Tank1.png");
        kPlane = new Texture("kamikaze_plane.png");
        plane = new TextureRegion(kPlane);
        terrain_texture = new Texture("Terrain_texture.png");
        crate = new Texture("Crate.png");
        parachute = new Texture("Parachute.png");
        settings = new Texture("Settings.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.44f,0.31f,0.96f, 0.5f);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.batch.draw(terrain, 0,-295, SCREEN_WIDTH, 500);
        for(int i = 0; i < 1600; i += 35) {
            game.batch.draw(terrain_texture, i, 196, 967*0.05f, 262*0.05f);
        }
        game.batch.draw(
                logo,
                (SCREEN_WIDTH/2 - BUTTON_WIDTH/2),
                500,
                BUTTON_WIDTH,
                BUTTON_HEIGHT*3
        );
        game.batch.draw(exitButtonInactive, BUTTON_RIGHT_LOCATION, 100, BUTTON_WIDTH, BUTTON_HEIGHT);

        if(Gdx.input.getX() < BUTTON_RIGHT_LOCATION + BUTTON_WIDTH && Gdx.input.getX() >BUTTON_RIGHT_LOCATION  && SCREEN_HEIGHT - Gdx.input.getY() < 100 + BUTTON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > 100){
            if(Gdx.input.isTouched()){
                this.dispose();
                Gdx.app.exit();
//                game.setScreen(new MainGameScreen(game));
            }
        }
        game.batch.draw(loadButtonInactive, BUTTON_RIGHT_LOCATION, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        if(Gdx.input.getX() < BUTTON_RIGHT_LOCATION + BUTTON_WIDTH && Gdx.input.getX() >BUTTON_RIGHT_LOCATION  && SCREEN_HEIGHT - Gdx.input.getY() < 200 + BUTTON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > 200){
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new LoadGameScreen(game));
            }
        }
        game.batch.draw(newButtonInactive, BUTTON_RIGHT_LOCATION, 300, BUTTON_WIDTH, BUTTON_HEIGHT);

        if(Gdx.input.getX() < BUTTON_RIGHT_LOCATION + BUTTON_WIDTH && Gdx.input.getX() >BUTTON_RIGHT_LOCATION  && Gdx.input.getY() < 300 + BUTTON_HEIGHT && Gdx.input.getY() > 300){
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new TankSelectScreen(game));
            }
        }
        game.batch.draw(tank, 50, 199, 423*0.7f, 229*0.7f);
        game.batch.draw(tank2, 730, 199, -446*0.7f, 226*0.7f);
        game.batch.draw(plane, 950, 470,0,0, 1091*0.25f, 694*0.25f,1,1,25f);
        game.batch.draw(parachute, 170, 530, 517*0.2f, 992*0.2f);
        game.batch.draw(crate, 204, 500, 192*0.2f, 164*0.2f);
        game.batch.draw(settings, 0, 50, 654*0.15f, 488*0.15f);
        if(Gdx.input.getX() < 98 && Gdx.input.getX() > 0 && SCREEN_HEIGHT - Gdx.input.getY() < 50 + 73 && SCREEN_HEIGHT - Gdx.input.getY() > 50){
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new SettingsMenuScreen(game));
            }
        }


        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
