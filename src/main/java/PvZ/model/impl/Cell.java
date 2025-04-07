package PvZ.model.impl;

import PvZ.model.api.Plant;

public class Cell {

    private Plant plant;
    private Zombie zombie;

    public Cell() {
        this.plant = null;
        this.zombie = null;
    }

    public boolean hasPlant() {
        return this.plant != null;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void removePlant() {
        this.plant = null;
    }


    public boolean hasZombie() {
        return this.zombie != null;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }

    public void removeZombie() {
        this.zombie = null;
    }

    @Override
    public String toString() {
        return "Cell[plant=" + (plant != null ? plant.getClass().getSimpleName() : "null") +
                ", zombie=" + (zombie != null ? zombie.getClass().getSimpleName() : "null") + "]";
    }
}
