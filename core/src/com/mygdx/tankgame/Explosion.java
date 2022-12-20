package com.mygdx.tankgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.tankgame.templates.PlayGame;

public class Explosion {
    public static final float FRAME_TIME = 0.2f;
    public static final float OFFSET = 3f;
    public static final int SIZE = 88;
    private static Animation<TextureRegion> animation = null;

    private float x;
    private float y;
    private float time;

    public boolean remove = false;

    public Explosion(float x, float y) {
        this.x = x - OFFSET;
        this.y = y - OFFSET;

        time = 0;
        if (animation == null) {
            animation = new Animation<TextureRegion>(FRAME_TIME, TextureRegion.split(new Texture("explosion2-removebg.png"), SIZE, SIZE)[0]);

        }
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void update(float deltatime) {
        time += deltatime;
        if (animation.isAnimationFinished(time)) {
            remove = true;
        }
    }
    public void render(Batch batch) {
        Sprite sprite = new Sprite(animation.getKeyFrame(time));
        System.out.println(sprite.getScaleX() + " " + sprite.getScaleY());
        sprite.scale(-0.5f);
        batch.draw(sprite, x, y, SIZE/15f, SIZE/15f);
    }
}
