package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;


public class ActualGame implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private TankGame game;
    public ActualGame (TankGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/50, Gdx.graphics.getHeight()/50);
        //metre:pixel 10:1
        //body definition
//        BodyDef tankDef = new BodyDef();
//        tankDef.type = BodyDef.BodyType.DynamicBody;
//        tankDef.position.set(0,0.5f);
//
//        PolygonShape shape = new PolygonShape();
//        Vector2[] vertices = {new Vector2(0f, 0f),
//                              new Vector2(0f, 1f),
//                              new Vector2(1f, 1f),
//                              new Vector2(1f, 0f)};
//        shape.set(vertices);
//        //fixture definition
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.density = 2.5f;
//        fixtureDef.friction = 0.25f;
//        fixtureDef.restitution = 0.75f;
//        fixtureDef.shape = shape;
//
//        world.createBody(tankDef).createFixture(fixtureDef);

        // SURFACE
        BodyDef surface = new BodyDef();
        surface.type = BodyDef.BodyType.StaticBody;
        surface.position.set(0,-1);

        EdgeShape surfaceShape = new EdgeShape();
//        Vector2[] surfaceVertices = {new Vector2(0f, 0f),
//                new Vector2(1f, 0f)};
        surfaceShape.set(-10, 0, 10, 0);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.density = 2.5f;
        fixtureDef2.friction = 1f;
        fixtureDef2.restitution = 0f;
        fixtureDef2.shape = surfaceShape;

        world.createBody(surface).createFixture(fixtureDef2);


        BodyDef bullet = new BodyDef();
        bullet.type = BodyDef.BodyType.DynamicBody;
        bullet.bullet = true;


        PolygonShape bShape = new PolygonShape();
        Vector2[] bVertices = {new Vector2(0f, 0f),
                new Vector2(0f, 0.1f),
                new Vector2(0.1f, 0.1f),
                new Vector2(0.1f, 0f)};
        bShape.set(bVertices);

        FixtureDef fixtureDef3 = new FixtureDef();
        fixtureDef3.density = 2.5f;
        fixtureDef3.friction = 1f;
        fixtureDef3.restitution = 0f;
        fixtureDef3.shape = bShape;


        Body bBody = world.createBody(bullet);
        bBody.createFixture(fixtureDef3);


        // when setting up the shot:
        bBody.setGravityScale(0f);

        // on shooting:
        bBody.setGravityScale(1f);
        bBody.setLinearVelocity(5,4);



        // TODO organise according to Class Diagram + incorporate joints for turret+body

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f,0f,0f, 0.5f);
        debugRenderer.render(world, camera.combined);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
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
