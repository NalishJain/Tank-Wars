package com.mygdx.tankgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.tankgame.templates.PauseGameScreen;

import java.io.*;

import static com.mygdx.tankgame.templates.PlayGame.*;

public class ClassGame implements Serializable {
    //Add-ons

//    private Array<Body> bodies = new Array<Body>();
    public int isGameBeingPlayed = 0;
    transient private Body Ground;
    transient private Sprite spriteGround;
    
    transient Texture hpBar = new Texture("newHP.png");
    transient Texture redHpBar = new Texture("redHP.png");
    transient Texture Shield = new Texture("Shield.png");

    transient Texture powerSlider = new Texture("powerSlider.png");

    private boolean weaponLaunched;


    //From Class-Diagram
    private Player player1;
    private Player player2;
    private GameObject[] gameObjects;
//    private SupplyDrop[] supplyDrops;
//    private ClassGame[] savedGames;
    private int serialVersionUID;
    private int curPlayer = 1; //1,2


    public ClassGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

//        player1 = new Player(1, 1);
//        player2 = new Player(2, 3);

        this.weaponLaunched = false;

        //Added Tanks for both player to keep record of turret
        //Initialized tank, turret and movement vector

    }

    public void gameOver() {

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

    public Player getCurPlayer() {
        return curPlayer == 1 ?  player1 : player2;
    }
    public int getCurPlayerNum() {
        return curPlayer;
    }

    public boolean isWeaponLaunched() {
        return weaponLaunched;
    }

    public void setWeaponLaunched(boolean weaponLaunched) {
        this.weaponLaunched = weaponLaunched;
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

//    public SupplyDrop[] getSupplyDrops() {
//        return supplyDrops;
//    }
//
//    public void setSupplyDrops(SupplyDrop[] supplyDrops) {
//        this.supplyDrops = supplyDrops;
//    }

    public int getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(int serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }


    public void setCurPlayer(int curPlayer) {
        this.curPlayer = curPlayer;
    }

    public void pauseGame(){}


//    public ClassGame[] getSavedGames() {
//        return savedGames;
//    }
//
//    public void setSavedGames(ClassGame[] savedGames) {
//        this.savedGames = savedGames;
//    }
    public boolean trySupplyDrop(){
        //will use random function to drop (1/5) uses function of supplyDrop class
        return false;
    }

    public void changeTurn(){
        if (curPlayer == 1) {
            curPlayer = 2;
        } else curPlayer = 1;
    }

    public void serialise() throws IOException {
        ObjectOutputStream toBeSavedGame = null;
        ObjectOutputStream booleanArray = null;
        try{
           this.player1.getPlayerTank().setPosition(new Position(this.player1.getTankBody().getPosition().x,this.player1.getTankBody().getPosition().y));
           this.player2.getPlayerTank().setPosition(new Position(this.player2.getTankBody().getPosition().x, this.player2.getTankBody().getPosition().y));

           this.player1.getPlayerTank().getTankTurret().setTurretAngle(this.player1.getTankTurretBody().getAngle());
           this.player1.getPlayerTank().getTankTurret().setPosition(new Position(this.player1.getTankTurretBody().getPosition().x,this.player1.getTankTurretBody().getPosition().y));

           this.player2.getPlayerTank().getTankTurret().setTurretAngle(this.player2.getTankTurretBody().getAngle());
           this.player2.getPlayerTank().getTankTurret().setPosition(new Position(this.player2.getTankTurretBody().getPosition().x,this.player2.getTankTurretBody().getPosition().y));

           if(!PauseGameScreen.gameSavedOrNot[0]){
               toBeSavedGame = new ObjectOutputStream(new FileOutputStream(PauseGameScreen.savedGames[0]));
               PauseGameScreen.gameSavedOrNot[0] = true;
           }
           else if(!PauseGameScreen.gameSavedOrNot[1]){
               toBeSavedGame = new ObjectOutputStream(new FileOutputStream(PauseGameScreen.savedGames[1]));
               PauseGameScreen.gameSavedOrNot[1] = true;

           }
           else if(PauseGameScreen.gameSavedOrNot[2]){
               toBeSavedGame = new ObjectOutputStream(new FileOutputStream(PauseGameScreen.savedGames[2]));
               PauseGameScreen.gameSavedOrNot[2] = true;
           }


            booleanArray = new ObjectOutputStream(new FileOutputStream("result.txt"));
            booleanArray.writeObject(PauseGameScreen.gameSavedOrNot);
            toBeSavedGame.writeObject(this);

        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        finally {
            toBeSavedGame.close();
            booleanArray.close();
        }
    }


    /* ADD-ON METHODS */
    public void showGround(TankGame runGame) {

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-4);

        // ChainShape groundShape = new ChainShape();
        // groundShape.createChain(new Vector2[]{new Vector2(-500, -3), new Vector2(500, -3)});

        EdgeShape groundShape = new EdgeShape();
        groundShape.set(new Vector2(-500, -3), new Vector2(500, -3));
        
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 0;

        Ground = world.createBody(bodyDef);
        Ground.createFixture(fixtureDef);

        groundShape.dispose();
    }

    public void showTanks() {
        if(isGameBeingPlayed == 0){
            player1.showTank();
            player2.showTank();
            isGameBeingPlayed++;
        }
        hpBar = new Texture("newHP.png");
        redHpBar = new Texture("redHP.png");
        Shield = new Texture("Shield.png");
        powerSlider = new Texture("powerSlider.png");

    }

    public void showPlayerHealthBars(TankGame runGame){

        float player1Health = this.player1.getPlayerTank().getTankHp();
        float player2Health = this.player2.getPlayerTank().getTankHp();

        runGame.batch.draw(hpBar, -13,  14, 285*pixelToMeters, 80*pixelToMeters);
        runGame.batch.draw(redHpBar, -12.75f + 260*pixelToMeters,  14.25f, -260*pixelToMeters*player1Health, 65*pixelToMeters);
        runGame.batch.draw(Shield,-6.3f,13f,153*0.9f*pixelToMeters,157*0.9f*pixelToMeters);

        runGame.batch.draw(hpBar, 4,  14, 285*pixelToMeters, 80*pixelToMeters);
        runGame.batch.draw(redHpBar, 4.53f ,  14.25f, 260*pixelToMeters*player2Health, 65*pixelToMeters);
        runGame.batch.draw(Shield,2.3f,13f,153*0.9f*pixelToMeters,157*0.9f*pixelToMeters);
    }

    public void showPowerSlider(TankGame runGame){
        float power = this.getCurPlayer().getPower();

        runGame.batch.draw(powerSlider, (-964*pixelToMeters/4f), -16, 964*pixelToMeters*0.5f, 368*pixelToMeters*0.5f);
        runGame.batch.draw(hpBar,  -470*pixelToMeters/2f-(power * 0.2f)+(1f-power)*0.24f+0.1f, -13.15f, 470f*pixelToMeters*power, 80f*pixelToMeters+0.15f);
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