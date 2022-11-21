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
    Texture tank;
    Texture bg;
    Texture terrain;
    Texture tank2;
    //    Texture kPlane;
    Texture aShip;
    Texture terrain_texture;
    Texture arrow;
    Texture banner;
    Texture hpBar;

    public LoadGameScreen(TankGame game){
        this.game = game;
        chooseButtonActive = new Texture("choose4x.png");
        chooseButtonInactive = new Texture("choose4xin.png");
        tank = new Texture("Tank2.png");
        bg = new Texture("bg3.png");
        terrain = new Texture("terrain2.png");
//        kPlane = new Texture("Plane.png");
        aShip = new Texture("AirShip.png");
        terrain_texture = new Texture("Terrain_texture.png");
        arrow = new Texture("arrow_left.png");
        banner = new Texture("Banner2.png");
        hpBar = new Texture("HPBar.png");
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
                chooseButtonInactive,
                (SCREEN_WIDTH/2 - BUTTON_WIDTH/2),
                50,
                BUTTON_WIDTH,
                BUTTON_HEIGHT
        );
        game.batch.draw(tank, (SCREEN_WIDTH/2 - 375/2)-5, 195, 375, 207);
//        game.batch.draw(kPlane, 900, 530, 1132*0.2f, 346*0.2f);
        game.batch.draw(aShip, 350, 500, -1890*0.15f, 897*0.15f);
        game.batch.draw(arrow, 400, 280, 31*1.5f, 46*1.5f);
        game.batch.draw(arrow, SCREEN_WIDTH-395, 280, -31*1.5f, 46*1.5f);
        game.batch.draw(banner, (SCREEN_WIDTH/2 - 4444*0.1f/2), 580, 4444*0.1f, 996*0.1f);
        game.batch.draw(hpBar, (SCREEN_WIDTH/2 - 3831*0.07f/2), 440, 3831*0.07f, 1072*0.07f);

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
