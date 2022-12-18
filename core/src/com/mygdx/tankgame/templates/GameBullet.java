package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.Position;
import com.mygdx.tankgame.TankGame;
import com.mygdx.tankgame.Projectile;
import java.lang.Math.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class GameBullet implements Screen {

    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Body bBody;
    private TankGame game;


    private Array<Body> bodies = new Array<Body>();

    private Body Ground;
    PrismaticJointDef jointDef1b;
    PrismaticJoint joint;
    private Body tank1Body;
    private Body tank1TurretBody;
    private Body tank2Body;
    private Body tank2TurretBody;
    private int tankSpeed = 300;
    private Vector2 tank1Movement =  new Vector2();
    private Vector2 tank2Movement =  new Vector2();

    private float turret_length = 1f;
    public GameBullet(TankGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/25, Gdx.graphics.getHeight()/25);
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
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-1);

        EdgeShape surfaceShape = new EdgeShape();
//        Vector2[] surfaceVertices = {new Vector2(0f, 0f),
//                new Vector2(1f, 0f)};
        surfaceShape.set(-50, 0, 50, 0);

        final FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.density = 2.5f;
        fixtureDef2.friction = 1f;
        fixtureDef2.restitution = 0f;
        fixtureDef2.shape = surfaceShape;

        world.createBody(bodyDef).createFixture(fixtureDef2);


        // BULLET



//        bullet.pPath(100f, 30f, bullet.getPosition());
//
//        // when setting up the shot:


        // on shooting:
//        bBody.setGravityScale(1f);
//        bBody.setLinearVelocity(5,4);


        // TODO nothing
        //tank1
        //Tank1Turret
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(-20,12);

        PolygonShape Tank1TurretShape = new PolygonShape();
        Tank1TurretShape.setAsBox(0.5f,0.05f);

        fixtureDef2.shape = Tank1TurretShape;
        fixtureDef2.friction = 0f;
        fixtureDef2.restitution = .4f;
        fixtureDef2.density = 5;
        tank1TurretBody = world.createBody(bodyDef);
        tank1TurretBody.createFixture(fixtureDef2);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(-20f, 10);


        //Tank1 backShape

        PolygonShape tank1BackShape = new PolygonShape();
        tank1BackShape.setAsBox(0.9f, 0.3f);

        fixtureDef2.shape = tank1BackShape;
        fixtureDef2.friction = 0.7f;
        fixtureDef2.restitution = 0f;
        fixtureDef2.density = 1000;

        tank1Body = world.createBody(bodyDef);
        tank1Body.createFixture(fixtureDef2);


        //Tank1 frontShape
        PolygonShape tank1FrontShape = new PolygonShape();
        tank1FrontShape.setAsBox(1, 0.4f);



        fixtureDef2.shape = tank1FrontShape;
        fixtureDef2.friction = 0.7f;
        fixtureDef2.restitution = .1f;
        fixtureDef2.density = 1000;


        tank1Body.createFixture(fixtureDef2);


        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.bodyA = tank1Body;
        jointDef.bodyB = tank1TurretBody;
        jointDef.maxMotorTorque = 0f;
        jointDef.motorSpeed = 500f;
//        jointDef.
        jointDef.localAnchorA.set(0.5f,1f);
//        jointDef.localAnchorB.set(-0.5f,0.9f);
        world.createJoint(jointDef);

        jointDef1b = new PrismaticJointDef();
        jointDef1b.bodyA = Ground;
        jointDef1b.bodyB = tank1Body;
        jointDef1b.localAxisA.set(1,0);
        jointDef1b.enableMotor = true;
        jointDef1b.maxMotorForce = 0;//this is a powerful machine after all...
        jointDef1b.motorSpeed = 5;
//        joint = (PrismaticJoint) world.createJoint(jointDef1b);

//        world.createJoint(jointDef1b);



        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                        tank1Movement.x = -tankSpeed*100;
                        break;
                    case Input.Keys.D:
                        tank1Movement.x = tankSpeed*100;

                        break;
                    case Input.Keys.LEFT:
                        tank2Movement.x = -tankSpeed;
                        break;
                    case Input.Keys.RIGHT:
                        tank2Movement.x = tankSpeed;
                        break;
                    case Input.Keys.E:
                        float total = tank1TurretBody.getAngle();
                        if (total < 0.9f) {
                            total = total + 0.02f;
                            tank1TurretBody.setTransform(-5f,0.8f,total);

                        }
                    case Input.Keys.G:
                        Projectile bullet = new Projectile(new Position(tank1TurretBody.getPosition().x+turret_length* (float)cos(tank1TurretBody.getAngle()), tank1TurretBody.getPosition().y+turret_length* (float)sin(tank1TurretBody.getAngle())));
                        bodyDef.type = BodyDef.BodyType.DynamicBody;
                        bodyDef.position.set(bullet.getPosition().getPosX(), bullet.getPosition().getPosY());
                        bodyDef.bullet = true;


                        fixtureDef2.density = 2.5f;
                        fixtureDef2.friction = 1f;
                        fixtureDef2.restitution = 0f;
                        fixtureDef2.shape = bullet.pShape;

                        bBody = world.createBody(bodyDef);
                        bBody.createFixture(fixtureDef2);
                        bBody.setGravityScale(0f);

                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode){
                    case Input.Keys.A:
                    case Input.Keys.D:
                        tank1Movement.x = 0;
                        tank1Body.setLinearVelocity(0f,0f);
                        break;
                    case Input.Keys.LEFT:
                    case Input.Keys.RIGHT:
                        tank2Movement.x = 0;
                        break;

                }
                return true;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                camera.zoom += amountX/25f;
                return true;
            }
        });


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

        tank1Body.applyForceToCenter(tank1Movement, true);
//        tank2Body.applyForceToCenter(tank2Movement,true);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            bBody.setGravityScale(1f);
            float angle = tank1TurretBody.getAngle();
            float power = 10f;
            float vx = (float) (power * cos(angle));
            float vy = (float) (power * sin(angle));


            bBody.setLinearVelocity(vx, vy);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            float total = tank1TurretBody.getAngle();
            if (total < 0.9f) {
                total = total + 0.02f;
                tank1TurretBody.setTransform(tank1TurretBody.getPosition().x, tank1TurretBody.getPosition().y, total);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            float total = tank1TurretBody.getAngle();
            if (total > 0f) {
                total = total - 0.02f;
                tank1TurretBody.setTransform(tank1TurretBody.getPosition().x, tank1TurretBody.getPosition().y, total);
            }
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

