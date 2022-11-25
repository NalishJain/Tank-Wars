package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class LoadGameScreen implements Screen {

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
    Texture game1;
    Texture game2;
    Texture game3;
    Texture playButton;
    Texture game1im;
    Texture game2im;

    public LoadGameScreen(TankGame game){
        this.game = game;
        chooseButtonActive = new Texture("LoadGameButton.png");
//        chooseButtonInactive = new Texture("LoadGameButton.png");
        bg = new Texture("bg3.png");
        terrain = new Texture("terrain2.png");
//        kPlane = new Texture("Plane.png");
        terrain_texture = new Texture("Terrain_texture.png");
        diagBox = new Texture("diagBox.png");
        backButton = new Texture("GoBack.png");
        game1 = new Texture("Game1inactive.png");
        game2 = new Texture("Game2inactive.png");
        game3 = new Texture("Game3disabled.png");
        playButton = new Texture("PlayButton.png");
        game1im = new Texture("game1image.png");
        game2im = new Texture("game2image.png");
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
        game.batch.draw(diagBox, (SCREEN_WIDTH/2 - 1000/2), (SCREEN_HEIGHT/2 - 600/2), 1000, 600);
        game.batch.draw(backButton, 0, 500, 654*0.15f, 488*0.15f);
        game.batch.draw(game1, 195, 228, 966*0.3f, 1284*0.3f);
        game.batch.draw(game2, (SCREEN_WIDTH/2 - 966*0.3f/2), 228, 966*0.3f, 1284*0.3f);
        game.batch.draw(game3, SCREEN_WIDTH-195-966*0.3f, 228, 966*0.3f, 1284*0.3f);
        game.batch.draw(playButton, (SCREEN_WIDTH/2 - 361/2), 100, 361, 108);
        game.batch.draw(game1im, 207, 381, 526*0.505f, 296*0.5f);
        game.batch.draw(game2im, 507, 381, 526*0.5055f, 296*0.5f);
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
