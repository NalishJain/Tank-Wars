package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class PauseGameScreen implements Screen {

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
    Texture resume;
    Texture saveButton;
    Texture quitButton;
    Texture soundButton;
    Texture musicButton;


    public PauseGameScreen(TankGame game){
        this.game = game;
        chooseButtonActive = new Texture("choose4x.png");
        chooseButtonInactive = new Texture("choose4xin.png");
        bg = new Texture("bg3.png");
        terrain = new Texture("terrain2.png");
//        kPlane = new Texture("Plane.png");
        terrain_texture = new Texture("Terrain_texture.png");
        diagBox = new Texture("diagBox.png");
        backButton = new Texture("GoBack.png");
        resume = new Texture("Resume.png");
        saveButton = new Texture("SaveButton.png");
        quitButton = new Texture("QuitButton.png");
        soundButton = new Texture("SoundButton.png");
        musicButton = new Texture("MusicButton.png");

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
        game.batch.draw(diagBox, (SCREEN_WIDTH/2 - 1000/2), (SCREEN_HEIGHT/2 - 700/2) , 1000, 700);
        game.batch.draw(backButton, 0, 500, 654*0.2f, 488*0.2f);
        game.batch.draw(resume,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) + 260 -27, 368,100);
        game.batch.draw(saveButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) + 150 - 27, 368,100);
        game.batch.draw(soundButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) +  13, 368,100);
        game.batch.draw(musicButton,(SCREEN_WIDTH/2 - 368/2) -2, (SCREEN_HEIGHT/2 - 100/2) -70 - 27, 368,100);
        game.batch.draw(quitButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) -180 -27, 368,100);






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
