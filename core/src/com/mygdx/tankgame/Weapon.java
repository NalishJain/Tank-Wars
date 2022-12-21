package com.mygdx.tankgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.tankgame.templates.PlayGame.pixelToMeters;

public class Weapon extends GameObject{
    private int damageRange;
    private int maxDamagePower;
    private Position weaponPosition;



    //add-ons:
//    public static Array<Weapon> allWeapons; // might not need this
    private boolean remove;
    transient private Sprite spriteWeapon;
    transient private Body weaponBody;
    private Position collisionPosition;

    public Weapon(int pnum, int chosenTank) {
        this.remove = false;
        if (chosenTank == 1) {
            // normal missile
            damageRange = 5;
            maxDamagePower = 200;

            spriteWeapon = new Sprite( new Texture("Weapon1.png") );
            if (pnum == 1) {
                spriteWeapon.setSize(331f*0.09f*pixelToMeters, 156f*0.09f*pixelToMeters);
            } else {
                spriteWeapon.setSize(-331f*0.09f*pixelToMeters, -156f*0.09f*pixelToMeters);

            }
            spriteWeapon.setOrigin(spriteWeapon.getWidth()/2, spriteWeapon.getHeight()/2);
        }
    }

    public Body getWeaponBody() {
        return weaponBody;
    }

    public void setWeaponBody(Body weaponBody) {
        this.weaponBody = weaponBody;
    }

    public Sprite getSpriteWeapon() {
        return spriteWeapon;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public Position getCollisionPosition() {
        return collisionPosition;
    }

    public void setCollisionPosition(Position collisionPosition) {
        this.collisionPosition = collisionPosition;
    }

    public void setSpriteWeapon(Sprite spriteWeapon) {
        this.spriteWeapon = spriteWeapon;
    }

    public int getDamageRange() {
        return damageRange;
    }

    public void setDamageRange(int damageRange) {
        this.damageRange = damageRange;
    }

    public int getMaxDamagePower() {
        return maxDamagePower;
    }

    public void setMaxDamagePower(int maxDamagePower) {
        this.maxDamagePower = maxDamagePower;
    }

    public Position getWeaponPosition() {
        return weaponPosition;
    }

    public void setWeaponPosition(Position weaponPosition) {
        this.weaponPosition = weaponPosition;
    }

    public Position projectilePath(){
        return null;
    }

    public void shoot(int power , int angle){

    }
}

