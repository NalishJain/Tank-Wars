package com.mygdx.tankgame;

import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.tankgame.templates.PlayGame.world;

public class BulletContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture f1 = contact.getFixtureA();
        Fixture f2 = contact.getFixtureB();

        if (f1 == null || f2 == null) return;
//        if (f1.getUserData() == null || f2.getUserData() == null) return;

        Weapon weapon;
        if (isBulletContact(f1, f2) == 1) {
            weapon = (Weapon)f1.getUserData();
            weapon.setCollisionPosition(new Position (weapon.getWeaponBody().getPosition().x, weapon.getWeaponBody().getPosition().y));
            //TODO add explosion
            weapon.setRemove(true);
        } else if (isBulletContact(f1, f2) == 2) {
            weapon = (Weapon)f2.getUserData();
            weapon.setCollisionPosition(new Position (weapon.getWeaponBody().getPosition().x, weapon.getWeaponBody().getPosition().y));
            //TODO add explosion
            weapon.setRemove(true);
        } else return;
    }

    private int isBulletContact(Fixture f1, Fixture f2) {
        if (f1.getUserData() instanceof Weapon) {
            return 1;
        }
        else if (f2.getUserData() instanceof Weapon) {
            return 2;
        }
        return 0;
    }
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
