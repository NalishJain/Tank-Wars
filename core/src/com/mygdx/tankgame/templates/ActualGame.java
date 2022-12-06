package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


public class ActualGame implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    @Override
    public void show() {
        world = new World(new Vector2(0, -9.18f), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth() /10, Gdx.graphics.getHeight()/10);
        //metre:pixel 10:1
        //body definition
        BodyDef ballDef = new BodyDef();
        ballDef.type = BodyDef.BodyType.DynamicBody;
        ballDef.position.set(0,1);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.25f);
        //fixture definition

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.75f;
        fixtureDef.shape = shape;

        world.createBody(ballDef).createFixture(fixtureDef);

    }

    @Override
    public void render(float delta) {
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);


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
