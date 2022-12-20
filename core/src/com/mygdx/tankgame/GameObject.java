package com.mygdx.tankgame;


import java.io.Serializable;

public class GameObject implements Serializable {
    private Position position = new Position();
    private long serialUID;
    public GameObject(){
    }
    public GameObject(Position p) {
        position = p;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public long getSerialUID() {
        return serialUID;
    }

    public void setSerialUID(long serialUID) {
        this.serialUID = serialUID;
    }

}