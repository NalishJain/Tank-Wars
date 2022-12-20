package com.mygdx.tankgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import static com.mygdx.tankgame.templates.PlayGame.*;
import static java.lang.Math.*;

public class Player implements Serializable{
    //Add-ons
    private Body tankBody;
    Texture redCross = new Texture("redCross.png");
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
    private Position TURRET_OFFSET;
    private Weapon curWeapon;

    //Most important co-ordinates
    private float turretSpriteOriginX, getTurretSpriteOriginY;
    private float offsetX, offsetY;


    //From Class-Diagram:
    private String name;
    private Tank playerTank;
    private Weapon[] curWeapons;
    private float power;
    private int angle = 30;
    private boolean isDoubleDamage = false;
    private boolean isDoubleFuel = false;
    private boolean isSkippedChance = false;
    Texture fuel = new Texture("fuel.png");

    public Player(int num, int chosenTank) {
        this.power = 1f;
        this.curWeapon = new Weapon(num, 1);
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
            TURRET_OFFSET = new Position(1.8f, 0f);
            spriteTank_f = new Sprite(new Texture("tank1front.png"));
            spriteTank_t = new Sprite(new Texture("tank1turret2.png"));
            spriteTank_b = new Sprite(new Texture("tank1back.png"));



        }
        else if(chosenTank == 2){
            TANK_SPRITE_B_LENGTH = 844;
            TANK_SPRITE_B_HEIGHT = 622;
            TANK_SPRITE_T_LENGTH = 858;
            TANK_SPRITE_T_HEIGHT = 166;
            TANK_SPRITE_F_LENGTH = 848;
            TANK_SPRITE_F_HEIGHT = 622;
            TURRET_OFFSET = new Position(0.5f, -0.3f);
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
            TURRET_OFFSET = new Position(1.8f, -0.3f);
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

    public Weapon getCurWeapon() {
        return curWeapon;
    }

    public void setCurWeapon(Weapon curWeapon) {
        this.curWeapon = curWeapon;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        System.out.println("Updating power;");
        if (power > 1f) {
            this.power = 1f;
            System.out.println(1);
        } else if (power < 0f) {
            this.power = 0f;
            System.out.println(2);
        } else {
            this.power = power;
            System.out.println(3);
        }
        System.out.println(power);
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
    public Position launchWeapon(float angle, Weapon weapon){
        curWeapon = weapon;
//        System.out.println("<<<<<<<<<<<<<THIS WORKS.");
        Projectile bullet;
        if (isPlayer1) {
            bullet = new Projectile(new Position(tankTurretBody.getPosition().x - TURRET_OFFSET.getPosX() + TANK_SPRITE_T_LENGTH / 300 * (float) cos(tankTurretBody.getAngle()),
                    tankTurretBody.getPosition().y + TURRET_OFFSET.getPosY() + TANK_SPRITE_T_LENGTH / 300 * (float) sin(tankTurretBody.getAngle())));
        } else {
            bullet = new Projectile(new Position(tankTurretBody.getPosition().x + TURRET_OFFSET.getPosX() + TANK_SPRITE_T_LENGTH / 300 * (float) cos(3.14159f + tankTurretBody.getAngle()),
                    tankTurretBody.getPosition().y + TURRET_OFFSET.getPosY() + TANK_SPRITE_T_LENGTH / 300 * (float) sin(3.14159f + tankTurretBody.getAngle())));
            System.out.println(TURRET_OFFSET);
        }

//        System.out.println(bullet.getPosition().getPosX() + " " + bullet.getPosition().getPosY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(bullet.getPosition().getPosX(), bullet.getPosition().getPosY());
        bodyDef.bullet = true;

        fixtureDef.density = 2.5f;
        fixtureDef.friction = 10f;
        fixtureDef.restitution = 0f;
        fixtureDef.shape = bullet.pShape;

        curWeapon.setWeaponBody(world.createBody(bodyDef));
        curWeapon.getWeaponBody().setTransform(bullet.getPosition().getPosX(), bullet.getPosition().getPosY(), tankTurretBody.getAngle());
//        System.out.println(tankTurretBody.getAngle());

        Fixture weaponFixture = curWeapon.getWeaponBody().createFixture(fixtureDef);
        weaponFixture.setUserData(weapon);

        float vx, vy;
        if (isPlayer1) {
            vx = (float) (25f * power * cos(angle));
            vy = (float) (25f * power * sin(angle));
        } else {
            vx = (float) (25f * power * cos(3.14159f + angle));
            vy = (float) (25f * power * sin(3.14159f + angle));
        }


        weapon.getWeaponBody().setLinearVelocity(vx, vy);
        return null;
    }
    public void renderWeapon(TankGame runGame) {
        curWeapon.getSpriteWeapon().setPosition(curWeapon.getWeaponBody().getPosition().x - curWeapon.getSpriteWeapon().getWidth()/2,
        curWeapon.getWeaponBody().getPosition().y - curWeapon.getSpriteWeapon().getHeight()/2);

        curWeapon.getSpriteWeapon().setRotation(curWeapon.getWeaponBody().getAngle() * MathUtils.radiansToDegrees);
        curWeapon.getSpriteWeapon().draw(runGame.batch);
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

    public void showFuel(TankGame runGame){
        runGame.batch.draw(fuel, -21,-13, 80*pixelToMeters,80*pixelToMeters);
        runGame.batch.draw(fuel, 21.4f,-13, -80*pixelToMeters,80*pixelToMeters);
        if(this.getPlayerTank().getFuel() < 0 && this.isPlayer1){
            runGame.batch.draw(redCross, -21.5f,-13, 95*pixelToMeters, 95*pixelToMeters);
        }
        if(this.getPlayerTank().getFuel() < 0 && !this.isPlayer1){
            runGame.batch.draw(redCross, 19.4f,-13, 95*pixelToMeters, 95*pixelToMeters);
        }
    }

    public int causeDamage(Weapon weapon, Position weaponPosition){
        // calculate distance
        if (isPlayer1) {
            double distance = sqrt(pow(this.tankBody.getPosition().x - weaponPosition.getPosX(), 2) + pow(this.tankBody.getPosition().y - weaponPosition.getPosY(), 2));
            System.out.println("Player 1: "+ distance);
            float damageScale = weapon.getDamageRange()-(float)distance;
            if (damageScale > 0) {
                float damageCaused = weapon.getMaxDamagePower()*(damageScale+1)/weapon.getDamageRange();
                float curHealth = this.getPlayerTank().getTankHp();
                this.getPlayerTank().setTankHp(curHealth - damageCaused/800f);
                System.out.println("Damage to Player1 : " + damageCaused);
                if (this.getPlayerTank().getTankHp() < 0) {
                    return 2;
                } else {
                    return 1;
                }
            }

        } else {
            double distance = sqrt(pow(this.tankBody.getPosition().x - weaponPosition.getPosX(), 2) + pow(this.tankBody.getPosition().y - weaponPosition.getPosY(), 2));
            System.out.println("Player 2: "+ distance);
            float damageScale = weapon.getDamageRange()-(float)distance;
            if (damageScale > 0) {
                float damageCaused = weapon.getMaxDamagePower() * (damageScale + 1) / weapon.getDamageRange();
                float curHealth = this.getPlayerTank().getTankHp();
                this.getPlayerTank().setTankHp(curHealth - damageCaused/800f);
                System.out.println("Damage to Player2 : " + damageCaused);
                if (this.getPlayerTank().getTankHp() < 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        // calculate damage caused using maxDamage and damageRange


        // decrease the health of this player's tank
        return 1;
    }

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }

//    public void move(ClassGame classGame){
//        float iniPos = this.getTankBody().getPosition().x;
//        float distanceTravelled
//    }


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
        fixtureDef.density = 3000;

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
