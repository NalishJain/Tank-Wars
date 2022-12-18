package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.Position;
import com.mygdx.tankgame.TankGame;
import com.mygdx.tankgame.Projectile;


public class ActualGameBullet implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Body bBody;
    private TankGame game;
    public ActualGameBullet(TankGame game) {
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
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-1);

        EdgeShape surfaceShape = new EdgeShape();
//        Vector2[] surfaceVertices = {new Vector2(0f, 0f),
//                new Vector2(1f, 0f)};
        surfaceShape.set(-10, 0, 10, 0);

        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.density = 2.5f;
        fixtureDef2.friction = 1f;
        fixtureDef2.restitution = 0f;
        fixtureDef2.shape = surfaceShape;

        world.createBody(bodyDef).createFixture(fixtureDef2);


        // BULLET
        Projectile bullet = new Projectile(new Position(0, 1));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(bullet.getPosition().getPosX(), bullet.getPosition().getPosY());
        bodyDef.bullet = true;


        fixtureDef2.density = 2.5f;
        fixtureDef2.friction = 1f;
        fixtureDef2.restitution = 0f;
        fixtureDef2.shape = bullet.pShape;

        bBody = world.createBody(bodyDef);
        bBody.createFixture(fixtureDef2);
//        bullet.pPath(100f, 30f, bullet.getPosition());
//
//        // when setting up the shot:
        bBody.setGravityScale(0f);

        // on shooting:
//        bBody.setGravityScale(1f);
//        bBody.setLinearVelocity(5,4);




        // TODO organise according to Class Diagram + incorporate joints for turret+body

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            // TODO move tank left if fuel > 0
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            // TODO move tank right if fuel < 0
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            // TODO move turret up
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            // TODO move turret down
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            // TODO increase power
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            // TODO decrease power
        }


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f,0f,0f, 0.5f);
        debugRenderer.render(world, camera.combined);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            bBody.setGravityScale(1f);
            bBody.setLinearVelocity(5, 4);
        }

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
