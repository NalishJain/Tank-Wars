package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.*;

public class PlayGame implements Screen {
    public final static World world = new World(new Vector2(0, -9.81f), true);
    private ClassGame classGame;
    private int tankSpeed = 300;
    private Box2DDebugRenderer debugRenderer;
    public static final float pixelToMeters = 1/32f;
    private OrthographicCamera camera;
    public final static BodyDef bodyDef = new BodyDef();
    public final static FixtureDef fixtureDef = new FixtureDef();


    TankGame runGame;

    public PlayGame(TankGame runGame, ClassGame classGame) {
        this.classGame = classGame;
        this.runGame = runGame;
        this.world.setContactListener(new BulletContactListener());
    }

    @Override
    public void show() {
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20); // maybe add Gdx.graphics.get...
        classGame.showGround();
        classGame.showTanks();
        Gdx.input.setInputProcessor(new GameInputController(){
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                        classGame.getCurPlayer().moveLeft();
                        break;
                    case Input.Keys.D:
                        classGame.getCurPlayer().moveRight();
                        break;
                    case Input.Keys.LEFT:
                        classGame.getCurPlayer().moveLeft();
                        break;
                    case Input.Keys.RIGHT:
                        classGame.getCurPlayer().moveRight();
                        break;
//                    case Input.Keys.SPACE:
//                        classGame.getCurPlayer().launchWeapon(10f, classGame.getCurPlayer().getTankTurretBody().getAngle(), new Weapon(1));
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                    case Input.Keys.D:
                        classGame.getCurPlayer().stopTank();
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.RIGHT:
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



        classGame.showGame(runGame);

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
//        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
//            classGame.getCurPlayer().getPlayerTank().getTankTurret().IncreaseTurretAngle2(classGame.getCurPlayer().getTankTurretBody());
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//            classGame.getCurPlayer().getPlayerTank().getTankTurret().DecreaseTurretAngle2(classGame.getCurPlayer().getTankTurretBody());
//        }

        /* BULLET HAS BEEN LAUNCHED */
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (classGame.isWeaponLaunched() == false) {
                classGame.getCurPlayer().launchWeapon(10f, classGame.getCurPlayer().getTankTurretBody().getAngle(), new Weapon(classGame.getCurPlayerNum(), 1));
            }
            classGame.setWeaponLaunched(true);

        }

        try {
            classGame.getCurPlayer().renderWeapon(runGame);
        } catch (NullPointerException e) {
        }


        runGame.batch.end();

        /* IF BULLET HAS EXPLODED */
        if (classGame.getCurPlayer().getCurWeapon().isRemove()) {
            world.destroyBody(classGame.getCurPlayer().getCurWeapon().getWeaponBody());
            classGame.getCurPlayer().getCurWeapon().setRemove(false);
            classGame.setWeaponLaunched(false);
            classGame.getPlayer1().causeDamage(classGame.getCurPlayer().getCurWeapon(), classGame.getCurPlayer().getCurWeapon().getCollisionPosition());
            classGame.getPlayer2().causeDamage(classGame.getCurPlayer().getCurWeapon(), classGame.getCurPlayer().getCurWeapon().getCollisionPosition());
            classGame.changeTurn();
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
