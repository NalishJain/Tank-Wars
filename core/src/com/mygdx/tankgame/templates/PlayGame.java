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
import com.mygdx.tankgame.ClassGame;
import com.mygdx.tankgame.GameInputController;
import com.mygdx.tankgame.TankGame;
import com.mygdx.tankgame.Weapon;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class PlayGame implements Screen {
    public final static World world = new World(new Vector2(0, -9.81f), true);
    private ClassGame classGame;
    Texture terrain = new Texture("terrain2.png");

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

    }

    @Override
    public void show() {
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/20, Gdx.graphics.getHeight()/20); // maybe add Gdx.graphics.get...
        classGame.showGround(runGame);
        classGame.showTanks();
        float inPos = classGame.getCurPlayer().getTankBody().getPosition().x;


        Gdx.input.setInputProcessor(new GameInputController(){
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                        classGame.getCurPlayer().moveLeft();
                        break;
                    case Input.Keys.D:
                        classGame.getCurPlayer().moveRight();
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
        world.step(1/60f, 8, 3);
//Applied Forces
        classGame.getCurPlayer().getTankBody().applyForceToCenter(classGame.getCurPlayer().getTankMovement(), true);
//        classGame.getPlayer2().getTankBody().applyForceToCenter(classGame.getPlayer2().getTankMovement(),true);

        runGame.batch.setProjectionMatrix(camera.combined);
        runGame.batch.begin();
        runGame.batch.draw(terrain, -35, -23, (SCREEN_WIDTH+900)*pixelToMeters, 500*pixelToMeters);
        classGame.showGame(runGame);
        classGame.showPlayerHealthBars(runGame);
        classGame.getCurPlayer().showFuel(runGame);

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

        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.D)){
            float iniPos = classGame.getCurPlayer().getTankBody().getPosition().x;
            distanceTravelled+= distanceTravelled + 0.00000000001f;
            if(distanceTravelled >= 900000000000f){
                classGame.getCurPlayer().stopTank();
            }
            classGame.getCurPlayer().getPlayerTank().setFuel(1 - distanceTravelled/900000000000f);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            classGame.getCurPlayer().launchWeapon(10f, classGame.getCurPlayer().getTankTurretBody().getAngle(), new Weapon(1));
            classGame.changeTurn();
            distanceTravelled = 0;
            classGame.getCurPlayer().getPlayerTank().setFuel(1);


        }

        try {
            classGame.getCurPlayer().renderWeapon(runGame);
        } catch (NullPointerException e) {
        }

        runGame.batch.end();
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
