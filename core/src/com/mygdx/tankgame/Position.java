package com.mygdx.tankgame;

import java.io.Serializable;

public class Position implements Serializable {
    private float posX = 0;
    private float posY = 0;
    public Position(){}

    public Position(float x, float y) {
        posX = x;
        posY = y;
    }
    public float getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }


}