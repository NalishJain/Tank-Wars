package com.mygdx.tankgame;

import com.badlogic.gdx.physics.box2d.Body;

public class Tank extends GameObject {

    Tank(){
        tankTurret = new Turret();
    }
    private int tankHp;
    private String tankName;//final
    private int tankAngle;
    private int fuel = 100;
    Turret tankTurret;
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

    public boolean isDead(){

        return false;
    }
    public class Turret {
        int turretAngle = 30;

        public int getTurretAngle() {
            return 0;
        }

        public void IncreaseTurretAngle(Body tankTurretBody) {
            float total = tankTurretBody.getAngle();
            if (total < 1.9f) {
                total = total + 0.002f;
                tankTurretBody.setTransform(tankTurretBody.getPosition().x, tankTurretBody.getPosition().y, total);
            }
        }
        public void DecreaseTurretAngle(Body tankTurretBody) {
            float total = tankTurretBody.getAngle();
            if (total > 0f) {
                total = total - 0.002f;
                tankTurretBody.setTransform(tankTurretBody.getPosition().x, tankTurretBody.getPosition().y, total);
            }
        }
        public void IncreaseTurretAngle2(Body tankTurretBody) {
            float total = tankTurretBody.getAngle();
            if (total > -1.9f) {
                total = total - 0.002f;
                tankTurretBody.setTransform(tankTurretBody.getPosition().x, tankTurretBody.getPosition().y, total);
            }
        }
        public void DecreaseTurretAngle2(Body tankTurretBody) {
            float total = tankTurretBody.getAngle();
            if (total < 0.2f) {
                total = total + 0.002f;
                tankTurretBody.setTransform(tankTurretBody.getPosition().x, tankTurretBody.getPosition().y, total);
            }
        }
    }
}

