package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.ClassGame;
import com.mygdx.tankgame.TankGame;

import java.io.IOException;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;
import static com.mygdx.tankgame.templates.PlayGame.world;

public class PauseGameScreen implements Screen {

    private static final int BUTTON_WIDTH = 350;
    private static final int BUTTON_HEIGHT = 100;
//    public static ClassGame[] savedGameArray = new ClassGame[3];
    public static String[]  savedGames = {"savedGame1.txt", "savedGame2.txt", "savedGame3.txt"};
    public static boolean[] gameSavedOrNot = {false, false, false};
    public static int noOfSavedGames = 0;

    OrthographicCamera camera;

    private static final int LEFT_WIDTH = SCREEN_WIDTH/2;
    private static final int RIGHT_WIDTH = SCREEN_WIDTH/2;
    private static final int BUTTON_RIGHT_LOCATION =  LEFT_WIDTH + (RIGHT_WIDTH/2 - BUTTON_WIDTH/2);

    TankGame game;
    Texture chooseButtonInactive;
    Texture chooseButtonActive;
    Texture bg;
    Sound sound;
    Texture terrain;
    Texture terrain_texture;
    Texture diagBox;
    Texture backButton;
    Texture resume;
    Texture saveButton;
    Texture quitButton;
    Texture soundButton;
    Texture musicButton;
    ClassGame classGame;


    public PauseGameScreen(TankGame game, ClassGame classGame){
        this.classGame = classGame;
        this.game = game;
//        chooseButtonActive = new Texture("choose4x.png");
//        chooseButtonInactive = new Texture("choose4xin.png");
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
        game.batch.draw(diagBox, (SCREEN_WIDTH/2 - 1000/2), (SCREEN_HEIGHT/2 - 700/2) , 1000, 700);
        game.batch.draw(backButton, 0, 500, 654*0.2f, 488*0.2f);

        if(Gdx.input.getX() < 0+654*0.2f  && Gdx.input.getX() > 0 && SCREEN_HEIGHT - Gdx.input.getY() < 500 + 488*0.2f  && SCREEN_HEIGHT - Gdx.input.getY() > 500){
            if(Gdx.input.isTouched()){
                long id = sound.play(1f);
                sound.setPitch(id,2);
                sound.setLooping(id,false);
                this.dispose();
                game.setScreen(new PlayGame(game, classGame));
            }
        }
        game.batch.draw(resume,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) + 260 -27, 368,100);

        if(Gdx.input.getX() < (SCREEN_WIDTH/2 - 368/2) +368  && Gdx.input.getX() >(SCREEN_WIDTH/2 - 368/2) && SCREEN_HEIGHT - Gdx.input.getY() < (SCREEN_HEIGHT/2 - 100/2) + 260 -27 + 100  && SCREEN_HEIGHT - Gdx.input.getY() > (SCREEN_HEIGHT/2 - 100/2) + 260 -27){
            if(Gdx.input.isTouched()){
                long id = sound.play(1f);
                sound.setPitch(id,2);
                sound.setLooping(id,false);
                this.dispose();
                game.setScreen(new PlayGame(game, classGame));;
            }
        }
        game.batch.draw(saveButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) + 150 - 27, 368,100);

        if(Gdx.input.getX() < (SCREEN_WIDTH/2 - 368/2) +368  && Gdx.input.getX() > (SCREEN_WIDTH/2 - 368/2) && SCREEN_HEIGHT - Gdx.input.getY() < (SCREEN_HEIGHT/2 - 100/2) + 150 - 27 + 100 && SCREEN_HEIGHT - Gdx.input.getY() > (SCREEN_HEIGHT/2 - 100/2) + 150 - 27){
            if(Gdx.input.justTouched()){
                long id = sound.play(1f);
                sound.setPitch(id,2);
                sound.setLooping(id,false);
                this.dispose();
                try {
                    classGame.serialise();
                    noOfSavedGames++;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                world.destroyBody(classGame.getPlayer1().getTankBody());
                world.destroyBody(classGame.getPlayer2().getTankBody());
                world.destroyBody(classGame.getPlayer1().getTankTurretBody());
                world.destroyBody(classGame.getPlayer2().getTankTurretBody());
                game.setScreen(new LoadGameScreen(game));
            }
        }

        game.batch.draw(soundButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) +  13, 368,100);
        game.batch.draw(musicButton,(SCREEN_WIDTH/2 - 368/2) -2, (SCREEN_HEIGHT/2 - 100/2) -70 - 27, 368,100);
        game.batch.draw(quitButton,(SCREEN_WIDTH/2 - 368/2), (SCREEN_HEIGHT/2 - 100/2) -180 -27, 368,100);

        if(Gdx.input.getX() < (SCREEN_WIDTH/2 - 368/2) +368  && Gdx.input.getX() > (SCREEN_WIDTH/2 - 368/2) && SCREEN_HEIGHT - Gdx.input.getY() < (SCREEN_HEIGHT/2 - 100/2) -180 -27 + 100 && SCREEN_HEIGHT - Gdx.input.getY() > (SCREEN_HEIGHT/2 - 100/2) -180 -27){
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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}