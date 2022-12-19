package com.mygdx.tankgame;

public class Weapon extends GameObject{
    private int damageRange;
    private int maxDamagePower;
    private Position weaponPosition;

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

