package com.mygdx.tankgame.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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


public class ActualGame implements Screen {

    private World world;
    private Array<Body> bodies = new Array<Body>();
    private float turret_length = 1f;
    private Body Ground;
    private Body bBody;
    private Body tank1Body;
    private Body tank1TurretBody;
    private Body tank2Body;
    private Body tank2TurretBody;
    private int tankSpeed = 300;
    private Vector2 tank1Movement =  new Vector2();
    private Vector2 tank2Movement =  new Vector2();
    private Box2DDebugRenderer debugRenderer;
    private final float pixelToMeters = 1/32f;
    private OrthographicCamera camera;
    Sprite spriteTank_b;
    Sprite spriteTank_f;
    Sprite spriteTank_t;

    Sprite spriteTank2_b;
    Sprite spriteTank2_f;
    Sprite spriteTank2_t;
    Sprite spriteMissile;
    public ActualGame(TankGame game){
        this.game = game;
    }
    TankGame game;
    public void show() {
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        //metre:pixel 10:1
        //body definition


        final BodyDef ballDef = new BodyDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(0.25f);
        //fixture definition

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.25f;
        fixtureDef.restitution = 0.75f;
        fixtureDef.shape = shape;


        //Ground
        //body def

        ballDef.type = BodyDef.BodyType.StaticBody;
        ballDef.position.set(0,-4);
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-500, 0), new Vector2(500, 0)});
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0;

        Ground = world.createBody(ballDef);
        Ground.createFixture(fixtureDef);

        groundShape.dispose();
        //tank1
        //Tank1Turret
        ballDef.type = BodyDef.BodyType.DynamicBody;
        ballDef.position.set(-20,12);
        tank1TurretBody = world.createBody(ballDef);

        PolygonShape Tank1TurretShape = new PolygonShape();
        Tank1TurretShape.setAsBox(0.5f,0.05f);

        fixtureDef.shape = Tank1TurretShape;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = .4f;
        fixtureDef.density = 5;

        Fixture tank1Tfixture = tank1TurretBody.createFixture(fixtureDef);
        spriteTank_t = new Sprite(new Texture("tank1turret2.png"));
        spriteTank_t.setSize(1016*0.15f*pixelToMeters, 316*0.15f*pixelToMeters);
        spriteTank_t.setOrigin(spriteTank_t.getWidth()/2, spriteTank_t.getHeight()/2);
        tank1Tfixture.setUserData(spriteTank_t);

        ballDef.type = BodyDef.BodyType.DynamicBody;
//        ballDef.position.set(-20f, 10);
        tank1Body = world.createBody(ballDef);

        //Tank1 backShape

        PolygonShape tank1BackShape = new PolygonShape();
        tank1BackShape.setAsBox(0.9f, 0.3f);

        fixtureDef.shape = tank1BackShape;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 1000;

        Fixture tank1Bfixture = tank1Body.createFixture(fixtureDef);

        spriteTank_b = new Sprite(new Texture("tank1back.png"));
        spriteTank_b.setSize(804*0.15f*pixelToMeters, 520*0.15f*pixelToMeters);
        spriteTank_b.setOrigin(spriteTank_b.getWidth()/2, spriteTank_b.getHeight()/2);
        tank1Bfixture.setUserData(spriteTank_b);

        //Tank1 frontShape
        PolygonShape tank1FrontShape = new PolygonShape();
        tank1FrontShape.setAsBox(1, 0.4f);



        fixtureDef.shape = tank1FrontShape;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 1000;


        Fixture tank1Ffixture =  tank1Body.createFixture(fixtureDef);
        spriteTank_f = new Sprite(new Texture("tank1front.png"));
        spriteTank_f.setSize(1008*0.15f*pixelToMeters, 516*0.15f*pixelToMeters);
        spriteTank_f.setOrigin(spriteTank_f.getWidth()/2, spriteTank_f.getHeight()/2);
        tank1Ffixture.setUserData(spriteTank_f);



        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.bodyA = tank1Body;
        jointDef.bodyB = tank1TurretBody;
        jointDef.maxMotorTorque = 0f;
        jointDef.motorSpeed = 500f;
        jointDef.localAnchorA.set(0.5f,1f);
        world.createJoint(jointDef);

//        jointDef1b = new PrismaticJointDef ();
//        jointDef1b.bodyA = Ground;
//        jointDef1b.bodyB = tank1Body;
//        jointDef1b.localAxisA.set(1,0);
//        jointDef1b.enableMotor = true;
//        jointDef1b.maxMotorForce = 0;//this is a powerful machine after all...
//        jointDef1b.motorSpeed = 5;
//        joint = (PrismaticJoint) world.createJoint(jointDef1b);
//        world.createJoint(jointDef1b);




        ballDef.type = BodyDef.BodyType.DynamicBody;
        ballDef.position.set(20f, 10);
        tank2Body = world.createBody(ballDef);

        //Tank2 backShape

        PolygonShape tank2BackShape = new PolygonShape();
        tank2BackShape.setAsBox(0.9f, 0.3f);

        fixtureDef.shape = tank2BackShape;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 1000;

        Fixture tank2Bfixture = tank2Body.createFixture(fixtureDef);

        spriteTank2_b = new Sprite(new Texture("Tank2Back.png"));
        spriteTank2_b.setSize(-844*0.13f*pixelToMeters, 662*0.13f*pixelToMeters);
        spriteTank2_b.setOrigin(spriteTank2_b.getWidth()/2, spriteTank2_b.getHeight()/2);
        tank2Bfixture.setUserData(spriteTank2_b);

        //Tank2Turret
        ballDef.type = BodyDef.BodyType.DynamicBody;
        ballDef.position.set(20,12);


        tank2TurretBody =  world.createBody(ballDef);
        PolygonShape Tank2TurretShape = new PolygonShape();

        Tank2TurretShape.setAsBox(0.5f,0.05f);
        fixtureDef.shape = Tank2TurretShape;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0.4f;
        fixtureDef.density = 5;
        Fixture tank2Tfixture =tank2TurretBody.createFixture(fixtureDef);
        spriteTank2_t = new Sprite(new Texture("Tank2Turret.png"));
        spriteTank2_t.setSize(-858*0.13f*pixelToMeters, 166*0.13f*pixelToMeters);
        spriteTank2_t.setOrigin(spriteTank2_t.getWidth()/2, spriteTank2_t.getHeight()/2);
        tank2Tfixture.setUserData(spriteTank2_t);

        //Tank2 frontShape
        PolygonShape tank2FrontShape = new PolygonShape();
        tank2FrontShape.setAsBox(1, 0.4f);



        fixtureDef.shape = tank2FrontShape;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 1000;


        Fixture tank2Ffixture =  tank2Body.createFixture(fixtureDef);
        spriteTank2_f = new Sprite(new Texture("Tank2Front.png"));
        spriteTank2_f.setSize(-848*0.13f*pixelToMeters, 618*0.13f*pixelToMeters);
        spriteTank2_f.setOrigin(spriteTank2_f.getWidth()/2, spriteTank2_f.getHeight()/2);
        tank2Ffixture.setUserData(spriteTank2_f);



        RevoluteJointDef jointDef2 = new RevoluteJointDef();
        jointDef2.bodyA = tank2Body;
        jointDef2.bodyB = tank2TurretBody;
        jointDef2.maxMotorTorque = 0f;
        jointDef2.motorSpeed = 500f;
        jointDef2.localAnchorA.set(0.5f,1f);
        world.createJoint(jointDef2);

        //Bullet
        bBody = world.createBody(ballDef);

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
                        tank2Movement.x = -tankSpeed*100;
                        break;
                    case Input.Keys.RIGHT:
                        tank2Movement.x = tankSpeed*100;
                        break;
                    case Input.Keys.W:
//                        float TankTotal1 = tank1TurretBody.getAngle();
//                        if(TankTotal1 < 0.9f){
//                            TankTotal1 = TankTotal1 + 0.02f;
//                            tank1TurretBody.setTransform(-5f,0.8f,TankTotal1);
//                        }
                        break;
                    case Input.Keys.S:
//                        float TankTotal3 = tank1TurretBody.getAngle();
//                        if(TankTotal3 > 0f){
//                            TankTotal3 = TankTotal3 - 0.02f;
//                            tank1TurretBody.setTransform(-5f,0.8f,TankTotal3);
//                        }
                        break;
                    case Input.Keys.E:
                        tank2TurretBody.setTransform(-5f,0.8f,-1.58f);
                        break;
                    case Input.Keys.DOWN:
                        float TankTotal2 = tank2TurretBody.getAngle();
                        TankTotal2 = TankTotal2 + 0.02f;
                        tank2TurretBody.setTransform(-5f,0.8f,TankTotal2);
                        break;
                    case Input.Keys.UP:
                        float TankTotal4 = tank2TurretBody.getAngle();
                        TankTotal4 = TankTotal4 - 0.02f;
                        tank2TurretBody.setTransform(-5f,0.8f,TankTotal4);
                        break;

                    case Input.Keys.G:

                        Projectile bullet = new Projectile(new Position(tank1TurretBody.getPosition().x+turret_length* (float)cos(tank1TurretBody.getAngle()), tank1TurretBody.getPosition().y+turret_length* (float)sin(tank1TurretBody.getAngle())));
                        ballDef.type = BodyDef.BodyType.DynamicBody;
                        System.out.println(bullet.getPosition().getPosX() + " " + bullet.getPosition().getPosY());
                        ballDef.position.set(bullet.getPosition().getPosX(), bullet.getPosition().getPosY());
                        ballDef.bullet = true;


                        fixtureDef.density = 2.5f;
                        fixtureDef.friction = 10f;
                        fixtureDef.restitution = 0f;
                        fixtureDef.shape = bullet.pShape;

                        spriteMissile = new Sprite( new Texture("Weapon1.png") );
                        spriteMissile.setSize(331f*0.09f*pixelToMeters, 156f*0.09f*pixelToMeters);
                        spriteMissile.setOrigin(spriteMissile.getWidth()/2, spriteMissile.getHeight()/2);
                        bBody = world.createBody(ballDef);
                        bBody.setTransform(bullet.getPosition().getPosX(), bullet.getPosition().getPosY(), tank1TurretBody.getAngle());
                        Fixture missileFixture = bBody.createFixture(fixtureDef);
                        missileFixture.setUserData(spriteMissile);
                        bBody.setGravityScale(0f);
                        break;
                    case Input.Keys.SPACE:
                        bBody.setGravityScale(1f);
                        float angle = tank1TurretBody.getAngle();
                        float power = 10f;
                        float vx = (float) (power * cos(angle));
                        float vy = (float) (power * sin(angle));


                        bBody.setLinearVelocity(vx, vy);
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
                    case Input.Keys.E:
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

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.44f,0.31f,0.96f, 0.5f);

        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 8, 3);
        tank1Body.applyForceToCenter(tank1Movement, true);
        tank2Body.applyForceToCenter(tank2Movement,true);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
//        world.getBodies(bodies);
//        bodies.add(tank1TurretBody);
//        bodies.add(tank1Body);

        Array<Fixture> fixtures = new Array<Fixture>();
        fixtures.add(tank1Body.getFixtureList().get(0));
        fixtures.add(tank1TurretBody.getFixtureList().get(0));
        fixtures.add(tank1Body.getFixtureList().get(1));

        fixtures.add(tank2Body.getFixtureList().get(0));
        fixtures.add(tank2TurretBody.getFixtureList().get(0));
        fixtures.add(tank2Body.getFixtureList().get(1));

        try {
            fixtures.add(bBody.getFixtureList().get(0));
        } catch (IndexOutOfBoundsException e) {
        }


        int i1 = 0;

//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            bBody.setGravityScale(1f);
//            float angle = tank1TurretBody.getAngle();
//            float power = 10f;
//            float vx = (float) (power * cos(angle));
//            float vy = (float) (power * sin(angle));
//
//
//            bBody.setLinearVelocity(vx, vy);
//        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            float total = tank1TurretBody.getAngle();
            if (total < 0.9f) {
                total = total + 0.002f;
                tank1TurretBody.setTransform(tank1TurretBody.getPosition().x, tank1TurretBody.getPosition().y, total);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            float total = tank1TurretBody.getAngle();
            if (total > 0f) {
                total = total - 0.002f;
                tank1TurretBody.setTransform(tank1TurretBody.getPosition().x, tank1TurretBody.getPosition().y, total);
            }
        }

        for(Fixture fixture : fixtures){
            i1++;
            if(fixture.getUserData() != null && fixture.getUserData() instanceof Sprite){

                Sprite sprite = (Sprite) fixture.getUserData();
                if(i1 != 2 && i1 != 5){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2, fixture.getBody().getPosition().y -sprite.getHeight()/2);
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                    sprite.draw(game.batch);
                }
                if(i1 == 2){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2 + 0.1f, fixture.getBody().getPosition().y -sprite.getHeight()/2 + 0.1f);
                    sprite.draw(game.batch);
                    sprite.setOrigin(0.77f,0.5f);
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                }
                if(i1 == 5){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2 -0.9f, fixture.getBody().getPosition().y -sprite.getHeight()/2 - 0.25f);
                    sprite.draw(game.batch);
                    sprite.setOrigin(-0.74f,0.095f);
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                }

            }
        }




        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/25;
        camera.viewportHeight = height/25;
        camera.update();


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