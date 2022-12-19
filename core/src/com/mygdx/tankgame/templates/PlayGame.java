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
import com.mygdx.tankgame.ClassGame;
import com.mygdx.tankgame.GameInputController;
import com.mygdx.tankgame.TankGame;

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
                        classGame.getPlayer1().moveLeft();
                        break;
                    case Input.Keys.D:
                        classGame.getPlayer1().moveRight();
                        break;
                    case Input.Keys.LEFT:
                        classGame.getPlayer2().moveLeft();
                        break;
                    case Input.Keys.RIGHT:
                        classGame.getPlayer2().moveRight();
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                    case Input.Keys.D:
                        classGame.getPlayer1().stopTank();
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.RIGHT:
                        classGame.getPlayer2().stopTank();
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
        classGame.getPlayer1().getTankBody().applyForceToCenter(classGame.getPlayer1().getTankMovement(), true);
        classGame.getPlayer2().getTankBody().applyForceToCenter(classGame.getPlayer2().getTankMovement(),true);

        runGame.batch.setProjectionMatrix(camera.combined);
        runGame.batch.begin();
        classGame.showGame(runGame);

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            classGame.getPlayer1().getPlayerTank().getTankTurret().IncreaseTurretAngle(classGame.getPlayer1().getTankTurretBody());
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            classGame.getPlayer1().getPlayerTank().getTankTurret().DecreaseTurretAngle(classGame.getPlayer1().getTankTurretBody());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            classGame.getPlayer2().getPlayerTank().getTankTurret().IncreaseTurretAngle2(classGame.getPlayer2().getTankTurretBody());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            classGame.getPlayer2().getPlayerTank().getTankTurret().DecreaseTurretAngle2(classGame.getPlayer2().getTankTurretBody());
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
