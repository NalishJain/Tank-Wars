package com.mygdx.tankgame;

public class Tank extends GameObject {
    private int tankHp;
    private String tankName;//final
    private int tankAngle;
    private int fuel = 100;
    private Turret tankTurret;
    private boolean canMove = true;
    private Weapon[] weaponList;

    public int getTankHp() {
        return tankHp;
    }

    public void setTankHp(int tankHp) {
        this.tankHp = tankHp;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }


    public int getTankAngle() {
        return tankAngle;
    }

    public void setTankAngle(int tankAngle) {
        this.tankAngle = tankAngle;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public Turret getTankTurret() {
        return tankTurret;
    }

    public void setTankTurret(Turret tankTurret) {
        this.tankTurret = tankTurret;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public Weapon[] getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(Weapon[] weaponList) {
        this.weaponList = weaponList;
    }
    class Turret{
        int turretAngle = 30;

        public int getTurretAngle(){

            return 0;
        }
        public void setTurretAngle(int angle){

        }
    }
    public boolean isDead(){

        return false;
    }
}