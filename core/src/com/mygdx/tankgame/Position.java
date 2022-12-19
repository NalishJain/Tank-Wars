package com.mygdx.tankgame;

public class Position implements Serializable{
    private float posX;
    private float posY;

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

    @Override
    public void serialise() {

    }

    @Override
    public void deserialise() {

    }
}