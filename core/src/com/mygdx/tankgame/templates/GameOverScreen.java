package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.tankgame.ClassGame;

import java.io.IOException;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;
import static com.mygdx.tankgame.templates.PlayGame.world;

public class GameOverScreen implements Screen {
    TankGame game;
    OrthographicCamera camera;
    Texture bg;
    Texture terrain;
    Texture terrain_texture;
    Texture diagBox;
    Texture backButton;
    Texture mainMenuButton;
    Texture playAgainButton;
    Texture gameOver;
    Texture P1Won;
    Texture P2Won;
    int pwon;
    ClassGame classGame;
    Sound sound;


    public GameOverScreen(TankGame game, ClassGame classGame, int pwon){
        this.game = game;
        this.pwon = pwon;
        this.classGame = classGame;

//        chooseButtonActive = new Texture("choose4x.png");
//        chooseButtonInactive = new Texture("choose4xin.png");
        bg = new Texture("bg3.png");
        terrain = new Texture("terrain2.png");
//        kPlane = new Texture("Plane.png");
        terrain_texture = new Texture("Terrain_texture.png");
        diagBox = new Texture("diagBox.png");
        backButton = new Texture("GoBack.png");
        mainMenuButton = new Texture("MainMenuButton.png");
        playAgainButton = new Texture("PlayAgainButton.png");
        gameOver = new Texture("GameOver.png");
        P1Won = new Texture("P1Won.png");
        P2Won = new Texture("P2Won.png");
    }
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight() );// maybe add Gdx.graphics.get...
        camera.position.set(640,360,0);
        camera.update();
        sound = Gdx.audio.newSound(Gdx.files.internal("click.wav"));


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.44f,0.31f,0.96f, 0.5f);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        game.batch.draw(terrain, 0,-295, SCREEN_WIDTH, 500);

        for(int i = 0; i < 1600; i += 35) {
            game.batch.draw(terrain_texture, i, 196, 967*0.05f, 262*0.05f);
        }
        game.batch.draw(diagBox, (SCREEN_WIDTH/2f - 1000/2f), (SCREEN_HEIGHT/2f - 700/2f) , 1000, 700);

        game.batch.draw(gameOver, (SCREEN_WIDTH/2f - 1180/4f), 525f, 1180/2f, 290/2f);
        game.batch.draw(mainMenuButton, 232, 50, 736/2f, 216/2f);
        game.batch.draw(playAgainButton, 672, 50, 736/2f, 216/2f);

        if (this.pwon == 1) {
            game.batch.draw(P1Won, -511, 175, 2025, 437);
        } else {
            game.batch.draw(P2Won, -511, 175, 2025, 437);
        }

        if(Gdx.input.getX() < 672 + 736/2f  && Gdx.input.getX() > 672 && SCREEN_HEIGHT - Gdx.input.getY() < 216/2f + 50  && SCREEN_HEIGHT - Gdx.input.getY() > 50){
            if(Gdx.input.isTouched()){
                long id = sound.play(1f);
                sound.setPitch(id,2);
                sound.setLooping(id,false);
                this.dispose();
                world.destroyBody(classGame.getPlayer1().getTankBody());
                world.destroyBody(classGame.getPlayer2().getTankBody());
                world.destroyBody(classGame.getPlayer1().getTankTurretBody());
                world.destroyBody(classGame.getPlayer2().getTankTurretBody());
                game.setScreen(new TankSelectScreen(game));;
            }
        }


        if(Gdx.input.getX() < 232 + 736/2f && Gdx.input.getX() > 232 && SCREEN_HEIGHT - Gdx.input.getY() < 216/2f + 50  && SCREEN_HEIGHT - Gdx.input.getY() > 50){
            if(Gdx.input.isTouched()){
                long id = sound.play(1f);
                sound.setPitch(id,2);
                sound.setLooping(id,false);
                this.dispose();
                world.destroyBody(classGame.getPlayer1().getTankBody());
                world.destroyBody(classGame.getPlayer2().getTankBody());
                world.destroyBody(classGame.getPlayer1().getTankTurretBody());
                world.destroyBody(classGame.getPlayer2().getTankTurretBody());
                game.setScreen(new FirstMenuScreen(game));
            }
        }


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

    }
}
