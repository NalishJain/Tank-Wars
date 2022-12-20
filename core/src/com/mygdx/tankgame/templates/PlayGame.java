package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.*;

import java.util.ArrayList;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class PlayGame implements Screen {
    public final static World world = new World(new Vector2(0, -9.81f), true);
    private ClassGame classGame;
    Texture terrain = new Texture("terrain2.png");
    Texture pauseButton = new Texture("PauseGameButton.png");
    ArrayList<Explosion> explosions;

    private int tankSpeed = 300;
    private Box2DDebugRenderer debugRenderer;
    private float distanceTravelled = 0;
    public static final float pixelToMeters = 1/32f;
    private OrthographicCamera camera;
    public final static BodyDef bodyDef = new BodyDef();
    public final static FixtureDef fixtureDef = new FixtureDef();

    Texture Shield = new Texture("shield.png");

    TankGame runGame;

    public PlayGame(TankGame runGame, ClassGame classGame) {
        this.classGame = classGame;
        this.runGame = runGame;

        this.world.setContactListener(new BulletContactListener());
        explosions = new ArrayList<Explosion>();

    }

    @Override
    public void show() {
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/20f, Gdx.graphics.getHeight()/20f); // maybe add Gdx.graphics.get...

        classGame.showGround(runGame);
        classGame.showTanks();
        float inPos = classGame.getCurPlayer().getTankBody().getPosition().x;


        Gdx.input.setInputProcessor(new GameInputController(){
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                        if (!classGame.isWeaponLaunched()) {
                            classGame.getCurPlayer().moveLeft();
                        }
                        break;
                    case Input.Keys.D:
                        if (!classGame.isWeaponLaunched()) {
                            classGame.getCurPlayer().moveRight();
                        }
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
//                        System.out.println(classGame.getCurPlayer().getTankBody().getPosition().x);
                    case Input.Keys.D:

                        classGame.getCurPlayer().stopTank();
                        break;
                }
                return true;
            }
        });


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        debugRenderer.render(world, camera.combined);

//Applied Forces
        classGame.getCurPlayer().getTankBody().applyForceToCenter(classGame.getCurPlayer().getTankMovement(), true);
//        classGame.getPlayer2().getTankBody().applyForceToCenter(classGame.getPlayer2().getTankMovement(),true);

        runGame.batch.setProjectionMatrix(camera.combined);
        runGame.batch.begin();
        runGame.batch.draw(terrain, -35, -23, (SCREEN_WIDTH+900)*pixelToMeters, 500*pixelToMeters);
        classGame.showGame(runGame);
        classGame.showPlayerHealthBars(runGame);
        classGame.showPowerSlider(runGame);
        classGame.getCurPlayer().showFuel(runGame);

        runGame.batch.draw(pauseButton, -32, 13.8f,654*pixelToMeters*0.2f, 488*pixelToMeters*0.2f);
//        if(Gdx.input.isTouched()){
//            System.out.println(Gdx.input.getX());
//            System.out.println(Gdx.input.getY());
//        }
        if(Gdx.input.getX() < 66  && Gdx.input.getX() > 0  &&  Gdx.input.getY() <90 && Gdx.input.getY() > 20){
            if(Gdx.input.isTouched()){
                runGame.setScreen(new PauseGameScreen(runGame));
            }
        }

        if (classGame.getCurPlayerNum() == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)){
                classGame.getCurPlayer().getPlayerTank().getTankTurret().IncreaseTurretAngle(classGame.getCurPlayer().getTankTurretBody());
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)){
                classGame.getCurPlayer().getPlayerTank().getTankTurret().DecreaseTurretAngle(classGame.getCurPlayer().getTankTurretBody());
            }
        } else {
            if (Gdx.input.isKeyPressed(Input.Keys.W)){
                classGame.getCurPlayer().getPlayerTank().getTankTurret().IncreaseTurretAngle2(classGame.getCurPlayer().getTankTurretBody());
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)){
                classGame.getCurPlayer().getPlayerTank().getTankTurret().DecreaseTurretAngle2(classGame.getCurPlayer().getTankTurretBody());
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            classGame.getCurPlayer().setPower(classGame.getCurPlayer().getPower() + 0.003f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            classGame.getCurPlayer().setPower(classGame.getCurPlayer().getPower() - 0.003f);
        }

        if( !classGame.isWeaponLaunched() && (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D))){
            float iniPos = classGame.getCurPlayer().getTankBody().getPosition().x;
            distanceTravelled+= distanceTravelled + 0.00000000001f;
            if(distanceTravelled >= 900000000000f){
                classGame.getCurPlayer().stopTank();
            }
            classGame.getCurPlayer().getPlayerTank().setFuel(1 - distanceTravelled/900000000000f);
        }


        // explosions
        ArrayList<Explosion> remExplosions = new ArrayList<Explosion>();
        for (Explosion e : explosions){
            e.update(delta);
            if (e.remove) {
                remExplosions.add(e);
            }
        }
        explosions.removeAll(remExplosions);
            


        /* BULLET HAS BEEN LAUNCHED */
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (classGame.isWeaponLaunched() == false) {
                classGame.getCurPlayer().launchWeapon(classGame.getCurPlayer().getTankTurretBody().getAngle(), new Weapon(classGame.getCurPlayerNum(), 1));
            }
            distanceTravelled = 0;
            classGame.getCurPlayer().getPlayerTank().setFuel(1);
            classGame.setWeaponLaunched(true);
        }


        try {
            classGame.getCurPlayer().renderWeapon(runGame);
        } catch (NullPointerException e) {
        }

        for (Explosion e : explosions) {
            e.render(runGame.batch);
        }

        runGame.batch.end();

        /* IF BULLET HAS EXPLODED */
        if (classGame.getCurPlayer().getCurWeapon().isRemove()) {
            world.destroyBody(classGame.getCurPlayer().getCurWeapon().getWeaponBody());
            explosions.add(new Explosion(classGame.getCurPlayer().getCurWeapon().getCollisionPosition().getPosX(), classGame.getCurPlayer().getCurWeapon().getCollisionPosition().getPosY()));
            classGame.getCurPlayer().getCurWeapon().setRemove(false);
            classGame.setWeaponLaunched(false);
            int isAlive1 = classGame.getPlayer1().causeDamage(classGame.getCurPlayer().getCurWeapon(), classGame.getCurPlayer().getCurWeapon().getCollisionPosition());
            int isAlive2 = classGame.getPlayer2().causeDamage(classGame.getCurPlayer().getCurWeapon(), classGame.getCurPlayer().getCurWeapon().getCollisionPosition());
            if (isAlive1 == 1 && isAlive2 == 1) {
                classGame.changeTurn();
            } else {
                classGame.setWeaponLaunched(true);
                classGame.gameOver();
            }
        }
        world.step(1/60f, 8, 3);
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
