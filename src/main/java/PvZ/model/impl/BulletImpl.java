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
        this.setPosition(this.pos); //THIS.POS + UNA CERTA POS CHE FA MOVE
        final List<Zombie> zombieList = GameModelImpl.getZombieList();
        final Optional<Zombie> zombie = this.giveZombieHitted(zombieList); //DOVREBBE PRENDE COME ARGOMENTO NA LISTA DE ZOMBIE VIVI
        if(zombie.isPresent()) { 
            zombie.decreaseLife(this.DAMAGE);
            this.alive = false;
        }
    }

    private Optional<Zombie> giveZombieHitted(final List<Zombie> zombieList) {
        return zombieList.stream().filter(z -> z.getPosition().equals(this.getPosition())).findFirst();
    }
    
}
