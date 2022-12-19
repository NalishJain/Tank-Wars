package com.mygdx.tankgame;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.tankgame.templates.PlayGame.*;

public class ClassGame implements Serializable {
    //Add-ons

    private Array<Body> bodies = new Array<Body>();
    private Body Ground;
    private Sprite spriteGround;
    private Body missileBody;
    private Sprite spriteMissile;

    //From Class-Diagram
    private Player player1;
    private Player player2;
    private GameObject[] gameObjects;
    private SupplyDrop[] supplyDrops;
    private ClassGame[] savedGames;
    private int serialVersionUID;
    private int curPlayer = 1; //1,2

    public ClassGame() {
        player1 = new Player(1, 1);
        player2 = new Player(2, 2);
        //Added Tanks for both player to keep record of turret
        //Initialized tank, turret and movement vector

    }


    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameObject[] getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(GameObject[] gameObjects) {
        this.gameObjects = gameObjects;
    }

    public SupplyDrop[] getSupplyDrops() {
        return supplyDrops;
    }

    public void setSupplyDrops(SupplyDrop[] supplyDrops) {
        this.supplyDrops = supplyDrops;
    }

    public int getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(int serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public int getCurPlayer() {
        return curPlayer;
    }

    public void setCurPlayer(int curPlayer) {
        this.curPlayer = curPlayer;
    }

    public void pauseGame(){}


    public ClassGame[] getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(ClassGame[] savedGames) {
        this.savedGames = savedGames;
    }
    public boolean trySupplyDrop(){
        //will use random function to drop (1/5) uses function of supplyDrop class
        return false;
    }

    public void changeTurn(){

    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }


    /* ADD-ON METHODS */
    public void showGround() {

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-4);
        ChainShape groundShape = new ChainShape();
        groundShape.createChain(new Vector2[]{new Vector2(-500, 0), new Vector2(500, 0)});
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0;

        Ground = world.createBody(bodyDef);
        Ground.createFixture(fixtureDef);

        groundShape.dispose();
    }

    public void showTanks() {
        player1.showTank();
        player2.showTank();
    }

    public void showGame(TankGame runGame){

        Array<Fixture> fixtures = new Array<Fixture>();

        fixtures.add(this.getPlayer1().getTankBody().getFixtureList().get(0));
        fixtures.add(this.getPlayer1().getTankTurretBody().getFixtureList().get(0));
        fixtures.add(this.getPlayer1().getTankBody().getFixtureList().get(1));
        fixtures.add(this.getPlayer2().getTankBody().getFixtureList().get(0));
        fixtures.add(this.getPlayer2().getTankTurretBody().getFixtureList().get(0));
        fixtures.add(this.getPlayer2().getTankBody().getFixtureList().get(1));
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
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2 + this.getPlayer1().getOffsetX(), fixture.getBody().getPosition().y -sprite.getHeight()/2 + this.getPlayer1().getOffsetY());
                    sprite.draw(runGame.batch);
                    sprite.setOrigin(this.getPlayer1().getTurretSpriteOriginX(),this.getPlayer1().getTurretSpriteOriginY());
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                }
                if(i1 == 5){
                    sprite.setPosition(fixture.getBody().getPosition().x - sprite.getWidth()/2 + this.getPlayer2().getOffsetX(), fixture.getBody().getPosition().y -sprite.getHeight()/2 + this.getPlayer2().getOffsetY());
                    sprite.draw(runGame.batch);
                    sprite.setOrigin(this.getPlayer2().getTurretSpriteOriginX(),this.getPlayer2().getTurretSpriteOriginY());
                    sprite.setRotation(fixture.getBody().getAngle()* MathUtils.radiansToDegrees);
                }

            }
        }
    }
}