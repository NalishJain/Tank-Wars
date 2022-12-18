package com.mygdx.tankgame.templates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.tankgame.TankGame;

import static com.mygdx.tankgame.TankGame.SCREEN_HEIGHT;
import static com.mygdx.tankgame.TankGame.SCREEN_WIDTH;

public class CopyMainGameScreen implements Screen{
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;

    TankGame game;
    Texture tank_f;
    Texture tank_t;
    Texture tank_b;

    Texture tank2_t;
    Texture tank2_b;
    Texture tank2_f;
    Texture bg, terrain;

    Texture terrain_texture;

    TextureRegion rtank_f;
    TextureRegion rtank_b;
    TextureRegion rtank_t;

    TextureRegion rtank2_f;
    TextureRegion rtank2_b;
    TextureRegion rtank2_t;

    Texture Shield;
    Texture hpBar;
    TextureRegion rtGrass;
    Texture tree1;
    Texture tree2;
    Texture tree3;
    Texture ePole, ePole2;

    Body ground;
    Body tank1Body;

    public CopyMainGameScreen(TankGame game){
        this.game = game;
        tank_b = new Texture("tank1back.png");
        tank_f = new Texture("tank1front.png");
        tank_t = new Texture("tank1turret2.png");
        tank2_t = new Texture("Tank2Turret.png");
        tank2_f = new Texture("Tank2Front.png");
        tank2_b = new Texture("Tank2Back.png");

        hpBar = new Texture("HPBar.png");
        Shield = new Texture("Shield.png");
        bg = new Texture("bg3.png");
        terrain = new Texture("terrain.png");

        rtank_f = new TextureRegion(tank_f);
        rtank_b = new TextureRegion(tank_b);
        rtank_t = new TextureRegion(tank_t);
        rtank2_b = new TextureRegion(tank2_b);
        rtank2_f = new TextureRegion(tank2_f);
        rtank2_t = new TextureRegion(tank2_t);

        ePole = new Texture("electricpole.png");
        ePole2 = new Texture("electricpole2.png");
//        rtGrass = new TextureRegion(terrain_texture);
        tree1 = new Texture("tree1.png");
        tree2 = new Texture("Tree2.png");
        tree3 = new Texture("Tree3.png");

    }
    @Override
    public void show() {
        world = new World(new Vector2(0, -9.18f), true);
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();

        //Initializing creater body and fixture
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0 ,-160);
        FixtureDef fixtureDef = new FixtureDef();

        //Initialised ground
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-500, 0), new Vector2(500, 0)});

        fixtureDef.shape = groundShape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;

        ground = world.createBody(bodyDef);
        ground.createFixture(fixtureDef);

        //Initialising Tank1

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(2.25f, 10);

        PolygonShape tank1Shape = new PolygonShape();
        tank1Shape.setAsBox(0.5f, 1);

        fixtureDef.shape = tank1Shape;
        fixtureDef.friction = 0.75f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 5;
        tank1Body = world.createBody(bodyDef);
        tank1Body.createFixture(fixtureDef);
//        tank1Body.setGravityScale(0.5f);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.44f,0.31f,0.96f, 0.5f);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        world.step(1/60f, 8, 3);

        game.batch.draw(ePole, 80, 200, 398*0.2f, 798*0.2f);
        game.batch.draw(ePole2, 1200, 200, 235*0.23f, 598*0.23f);
        game.batch.draw(tree1, 400, 200, 340*0.2f, 296*0.2f);
        game.batch.draw(tree2, 700, 200, 249*0.2f, 309*0.2f);
        game.batch.draw(tree3, 850, 200, 180*0.2f, 247*0.2f);
        game.batch.draw(tree2, 100, 200, 249*0.2f, 309*0.2f);
        game.batch.draw(tree1, 20, 200, 340*0.2f, 296*0.2f);
        game.batch.draw(tree1, 1200, 200, 340*0.2f, 296*0.2f);
        game.batch.draw(terrain, -SCREEN_WIDTH/2 + 400, -SCREEN_HEIGHT/2 +65, SCREEN_WIDTH+400, 500);

        game.batch.draw(rtank_b, tank1Body.getPosition().x + SCREEN_WIDTH/2, tank1Body.getPosition().y +SCREEN_HEIGHT/2, 0, 0, 804*0.15f, 520*0.15f, 1, 1, 0);
        game.batch.draw(rtank_t, tank1Body.getPosition().x + SCREEN_WIDTH/2, tank1Body.getPosition().y +SCREEN_HEIGHT/2, 0, 0, 1016*0.15f, 316*0.15f, 1, 1, 16f);
        game.batch.draw(rtank_f, tank1Body.getPosition().x + SCREEN_WIDTH/2, tank1Body.getPosition().y+SCREEN_HEIGHT/2, 0, 0, 1008*0.15f, 516*0.15f, 1, 1, 0);



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
