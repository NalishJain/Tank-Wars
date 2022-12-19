package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class SettingsMenuScreen implements Screen {

        private static final int BUTTON_WIDTH = 350;
        private static final int BUTTON_HEIGHT = 100;

        private static final int LEFT_WIDTH = SCREEN_WIDTH/2;
        private static final int RIGHT_WIDTH = SCREEN_WIDTH/2;
        private static final int BUTTON_RIGHT_LOCATION =  LEFT_WIDTH + (RIGHT_WIDTH/2 - BUTTON_WIDTH/2);

        TankGame game;
        Texture chooseButtonInactive;
        Texture chooseButtonActive;
        Texture bg;
        Texture terrain;
        Texture terrain_texture;
        Texture diagBox;
        Texture backButton;
        Texture Settings;
        Texture saveButton;
        Texture FeedBackButton;
        Texture soundButton;
        Texture musicButton;
        Texture Treasure;


    public SettingsMenuScreen(TankGame game){
        this.game = game;
//        chooseButtonActive = new Texture("choose4x.png");
//        chooseButtonInactive = new Texture("choose4xin.png");
        bg = new Texture("bg3.png");
        terrain = new Texture("terrain2.png");
//        kPlane = new Texture("Plane.png");
        terrain_texture = new Texture("Terrain_texture.png");
        diagBox = new Texture("diagBox.png");
        backButton = new Texture("GoBack.png");
        Settings = new Texture("SettingsButton.png");
        saveButton = new Texture("SaveButton.png");
        FeedBackButton = new Texture("Feedback.png");
        soundButton = new Texture("SoundButton.png");
        musicButton = new Texture("MusicButton.png");
        Treasure = new Texture("Treasure.png");

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
        game.batch.draw(diagBox, (SCREEN_WIDTH/2 - 800/2), (SCREEN_HEIGHT/2 - 700/2) , 800, 700);
        game.batch.draw(backButton, 0, 500, 654*0.2f, 488*0.2f);

        if(Gdx.input.getX() < 654*0.2f && Gdx.input.getX() > 0 && SCREEN_HEIGHT - Gdx.input.getY() < 500 + 488*0.2f && SCREEN_HEIGHT - Gdx.input.getY() > 500){
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new FirstMenuScreen(game));
            }
        }
        game.batch.draw(Settings,(SCREEN_WIDTH/2 - 450/2), (SCREEN_HEIGHT/2 - 120/2) + 260 -27 + 17, 450,120);
//        game.batch.draw(saveButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) + 150 - 27, 368,100);
        game.batch.draw(soundButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) +  13 + 110 - 8, 368,100);
        game.batch.draw(musicButton,(SCREEN_WIDTH/2 - 368/2) -2, (SCREEN_HEIGHT/2 - 100/2) -70 - 27 +110 -8, 368,100);
        game.batch.draw(FeedBackButton,(SCREEN_WIDTH/2 - 368/2) + 2, (SCREEN_HEIGHT/2 - 100/2) -180 -27 +110 -8, 368,100);
        game.batch.draw(Treasure, 0,0,400,300);






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

