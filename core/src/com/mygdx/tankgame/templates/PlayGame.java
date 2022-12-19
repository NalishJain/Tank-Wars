package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.ClassGame;
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


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1f);
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 8, 3);

        runGame.batch.setProjectionMatrix(camera.combined);
        runGame.batch.begin();
//        world.getBodies(bodies);
//        bodies.add(tank1TurretBody);
//        bodies.add(tank1Body);

        Array<Fixture> fixtures = new Array<Fixture>();
        fixtures.add(classGame.getPlayer1().getTankBody().getFixtureList().get(0));
        fixtures.add(classGame.getPlayer1().getTankTurretBody().getFixtureList().get(0));
        fixtures.add(classGame.getPlayer1().getTankBody().getFixtureList().get(1));

        fixtures.add(classGame.getPlayer2().getTankBody().getFixtureList().get(0));
        fixtures.add(classGame.getPlayer2().getTankTurretBody().getFixtureList().get(0));
        fixtures.add(classGame.getPlayer2().getTankBody().getFixtureList().get(1));

        int i1 = 0;
        for(Fixture fixture : fixtures){
            i1++;
            if(fixture.getUserData() != null && fixture.getUserData() instanceof Sprite){

                Sprite sprite = (Sprite) fixture.getUserData();
                if(i1 != 2 && i1 != 5){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2, fixture.getBody().getPosition().y -sprite.getHeight()/2);
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                    sprite.draw(runGame.batch);
                }
                if(i1 == 2){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2 + 0.1f, fixture.getBody().getPosition().y -sprite.getHeight()/2 + 0.1f);
                    sprite.draw(runGame.batch);
                    sprite.setOrigin(0.77f,0.5f);
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                }
                if(i1 == 5){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2 -0.9f, fixture.getBody().getPosition().y -sprite.getHeight()/2 - 0.25f);
                    sprite.draw(runGame.batch);
                    sprite.setOrigin(-0.74f,0.095f);
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                }

            }
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
