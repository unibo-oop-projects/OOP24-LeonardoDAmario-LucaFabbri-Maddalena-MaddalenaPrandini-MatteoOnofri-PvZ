package PvZ.model.impl;

import java.util.List;
import java.util.Optional;

import PvZ.model.api.Bullet;
import PvZ.model.api.Zombie;
import PvZ.utilities.Position;

public class BulletImpl extends AbstractEntity implements Bullet{

    private final int DAMAGE=25;
    private Position pos;
    private boolean alive;

    BulletImpl(final Position pos) {
        this.pos = pos;
        this.alive = true;
    }

    public int getDamage() {
        return this.DAMAGE;
    }

    @Override
    public void update() {
        if(this.alive) {
            this.setPosition(this.pos); //THIS.POS + UNA CERTA POS CHE FA MOVE
        }
    }

    public void die() {
        this.alive = false;
    }
    
}
