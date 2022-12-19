package com.mygdx.tankgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import static com.mygdx.tankgame.templates.PlayGame.*;

public class Player implements Serializable{
    //Add-ons
    private Body tankBody;
    private Body tankTurretBody;
    private boolean isPlayer1 = true; // to check whether right sided tank or left sided tank
    private Vector2 tankMovement = new Vector2();
    Sprite spriteTank_b, spriteTank_f, spriteTank_t;
    private int chosenTank;
    private float TANK_SPRITE_B_LENGTH;
    private float TANK_SPRITE_B_HEIGHT;
    private float TANK_SPRITE_T_LENGTH;
    private float TANK_SPRITE_T_HEIGHT;
    private float TANK_SPRITE_F_LENGTH;
    private float TANK_SPRITE_F_HEIGHT;

    //Most important co-ordinates
    private float turretSpriteOriginX, getTurretSpriteOriginY;
    private float offsetX, offsetY;


    //From Class-Diagram:
    private String name;
    private Tank playerTank;
    private Weapon[] curWeapons;
    private int power = 50;
    private int angle = 30;
    private boolean isDoubleDamage = false;
    private boolean isDoubleFuel = false;
    private boolean isSkippedChance = false;

    public Player(int num, int chosenTank) {
        if (num == 1) {
            this.isPlayer1 = true;
        } else if (num == 2) {
            this.isPlayer1 = false;
        }
        this.chosenTank = chosenTank;
        this.tankMovement = new Vector2();
        this.playerTank = new Tank();
        if (chosenTank == 1) {
            TANK_SPRITE_B_LENGTH = 804;
            TANK_SPRITE_B_HEIGHT = 520;
            TANK_SPRITE_T_LENGTH = 1016;
            TANK_SPRITE_T_HEIGHT = 316;
            TANK_SPRITE_F_LENGTH = 1008;
            TANK_SPRITE_F_HEIGHT = 516;
            spriteTank_f = new Sprite(new Texture("tank1front.png"));
            spriteTank_t = new Sprite(new Texture("tank1turret2.png"));
            spriteTank_b = new Sprite(new Texture("tank1back.png"));



        }
        // add other measurements - Tank3Left
        else if(chosenTank == 2){
            TANK_SPRITE_B_LENGTH = 844;
            TANK_SPRITE_B_HEIGHT = 622;
            TANK_SPRITE_T_LENGTH = 858;
            TANK_SPRITE_T_HEIGHT = 166;
            TANK_SPRITE_F_LENGTH = 848;
            TANK_SPRITE_F_HEIGHT = 622;
            spriteTank_f = new Sprite(new Texture("Tank2Front.png"));
            spriteTank_t = new Sprite(new Texture("Tank2Turret.png"));
            spriteTank_b = new Sprite(new Texture("Tank2Back.png"));
        }
        else if(chosenTank == 3){
            TANK_SPRITE_B_LENGTH = 884;
            TANK_SPRITE_B_HEIGHT = 416;
            TANK_SPRITE_T_LENGTH = 958;
            TANK_SPRITE_T_HEIGHT = 276;
            TANK_SPRITE_F_LENGTH = 810;
            TANK_SPRITE_F_HEIGHT = 478;
            spriteTank_f = new Sprite(new Texture("tank3front.png"));
            spriteTank_t = new Sprite(new Texture("tank3turret.png"));
            spriteTank_b = new Sprite(new Texture("tank3back.png"));
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public Weapon[] getCurWeapons() {
        return curWeapons;
    }

    public void setCurWeapons(Weapon[] curWeapons) {
        this.curWeapons = curWeapons;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public boolean isDoubleDamage() {
        return isDoubleDamage;
    }

    public void setDoubleDamage(boolean doubleDamage) {
        isDoubleDamage = doubleDamage;
    }

    public boolean isDoubleFuel() {
        return isDoubleFuel;
    }

    public void setDoubleFuel(boolean doubleFuel) {
        isDoubleFuel = doubleFuel;
    }

    public boolean isSkippedChance() {
        return isSkippedChance;
    }

    public void setSkippedChance(boolean skippedChance) {
        isSkippedChance = skippedChance;
    }

    public void showWeaponTrail(int angle, int power){

    }
    public void showTimer(){}
    public Weapon selectWeapons(){

        return null;
    }
    public Position launchWeapon(int power, int angle, Weapon weapon){

        return null;
    }
    public Position launchSplitterWeapon(int power, int angle, Weapon weapon){

        return null;
    }
    public void moveRight(){
        this.getTankMovement().x = 50000;
    }
    public void moveLeft(){
        this.getTankMovement().x = -50000;
    }

    public void stopTank(){
        this.getTankMovement().x = 0;
        this.getTankBody().setLinearVelocity(0,0);
    }
    public int causeDamage(Position weaponPosition){

        return 0;
    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }



    /* ADD-ON METHODS */

    public Body getTankBody() {
        return tankBody;
    }

    public void setTankBody(Body tankBody) {
        this.tankBody = tankBody;
    }

    public Body getTankTurretBody() {
        return tankTurretBody;
    }

    public void setTankTurretBody(Body tankTurretBody) {
        this.tankTurretBody = tankTurretBody;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public void setPlayer1(boolean player1) {
        isPlayer1 = player1;
    }

    public Vector2 getTankMovement() {
        return tankMovement;
    }

    public void setTankMovement(Vector2 tankMovement) {
        this.tankMovement = tankMovement;
    }

    public void showTank() {
        //Turret
        System.out.println("<<<<<<<<<<<This works.");
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        if (isPlayer1) {
            bodyDef.position.set(-20, 12);
        } else {
            bodyDef.position.set(20, 12);
        }
        tankTurretBody = world.createBody(bodyDef);

        PolygonShape TankTurretShape = new PolygonShape();
        TankTurretShape.setAsBox(0.5f,0.05f);

        fixtureDef.shape = TankTurretShape;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = .4f;
        fixtureDef.density = 5;

        Fixture tankTfixture = tankTurretBody.createFixture(fixtureDef);
        if (isPlayer1) {
            spriteTank_t.setSize(TANK_SPRITE_T_LENGTH * 0.15f * pixelToMeters, TANK_SPRITE_T_HEIGHT * 0.15f * pixelToMeters);
            spriteTank_t.setOrigin(spriteTank_t.getWidth() / 2, spriteTank_t.getHeight() / 2);
        } else {
            spriteTank_t.setSize(-TANK_SPRITE_T_LENGTH * 0.15f * pixelToMeters, TANK_SPRITE_T_HEIGHT * 0.15f * pixelToMeters);
            spriteTank_t.setOrigin(spriteTank_t.getWidth() / 2, spriteTank_t.getHeight() / 2);
        }
        tankTfixture.setUserData(spriteTank_t);




        //Back
        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        ballDef.position.set(-20f, 10);
        tankBody = world.createBody(bodyDef);

        PolygonShape tankBackShape = new PolygonShape();
        tankBackShape.setAsBox(0.9f, 0.3f);

        fixtureDef.shape = tankBackShape;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 1000;

        Fixture tankBfixture = tankBody.createFixture(fixtureDef);

        if (isPlayer1) {
            spriteTank_b.setSize(TANK_SPRITE_B_LENGTH*0.15f*pixelToMeters, TANK_SPRITE_B_HEIGHT*0.15f*pixelToMeters);
            spriteTank_b.setOrigin(spriteTank_b.getWidth()/2, spriteTank_b.getHeight()/2);
        } else {
            spriteTank_b.setSize(-TANK_SPRITE_B_LENGTH*0.15f*pixelToMeters, TANK_SPRITE_B_HEIGHT*0.15f*pixelToMeters);
            spriteTank_b.setOrigin(spriteTank_b.getWidth()/2, spriteTank_b.getHeight()/2);
        }

        tankBfixture.setUserData(spriteTank_b);




        //Front
        PolygonShape tank1FrontShape = new PolygonShape();
        tank1FrontShape.setAsBox(1, 0.4f);



        fixtureDef.shape = tank1FrontShape;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 1000;


        Fixture tankFfixture =  tankBody.createFixture(fixtureDef);
        if (isPlayer1) {
            spriteTank_f.setSize(TANK_SPRITE_F_LENGTH*0.15f*pixelToMeters, TANK_SPRITE_F_HEIGHT*0.15f*pixelToMeters);
            spriteTank_f.setOrigin(spriteTank_f.getWidth()/2, spriteTank_f.getHeight()/2);
        } else {
            spriteTank_f.setSize(-TANK_SPRITE_F_LENGTH*0.15f*pixelToMeters, TANK_SPRITE_F_HEIGHT*0.15f*pixelToMeters);
            spriteTank_f.setOrigin(spriteTank_f.getWidth()/2, spriteTank_f.getHeight()/2);
        }

        tankFfixture.setUserData(spriteTank_f);

        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.bodyA = tankBody;
        jointDef.bodyB = tankTurretBody;
        jointDef.maxMotorTorque = 0f;
        jointDef.motorSpeed = 500f;
        if (isPlayer1) {
            jointDef.localAnchorA.set(0.5f, 1f);
        } else {
            jointDef.localAnchorA.set(-0.5f, 1f);
        }
        world.createJoint(jointDef);
    }


    public float getTurretSpriteOriginX(){
        if(isPlayer1){
            if(chosenTank == 1){
                return 0.77f;
            }
            else if(chosenTank == 2){
                return 0.74f;
            }
            else if(chosenTank == 3){

            }
        }
        else{
            if(chosenTank == 1){
                return -0.77f;
            }
            else if(chosenTank == 2){
                return -0.74f;
            }
            else if(chosenTank == 3){

            }
        }
        return 0;
    }

    public float getTurretSpriteOriginY(){
        if(chosenTank == 1){
            return 0.5f;
        }
        else if(chosenTank == 2){
            return 0.095f;
        }
        else if(chosenTank == 3){

        }
        return 0;
    }

    public float getOffsetX(){
        if(isPlayer1){
            if(chosenTank == 1){
                return  0.1f;
            }
            else if(chosenTank == 2){
                return 0.9f;
            }
            else if(chosenTank == 3){
                return 0.7f;
            }
        }
        else{
            if(chosenTank == 1){
                return -0.1f;
            }
            else if(chosenTank == 2){
                return  -0.9f;
            }
            else if(chosenTank == 3){
                return -0.7f;
            }
        }
        return 0;
    }

    public float getOffsetY(){
        if(chosenTank == 1){
            return 0.1f;
        }
        else if(chosenTank == 2){
            return -0.25f;
        }
        else if(chosenTank == 3){

        }
        return 0;
    }
}
