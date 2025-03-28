package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.utilities.Position;

public class PlantFactory {
    public enum PLANT_NAMES{
        PEASHOOTER,
        SUNFLOWER,
        WALLNUT
    }

    public static BasePlant createPlant(PLANT_NAMES type, Position position) {
        BasePlant plant= switch(type){
            case PEASHOOTER -> new Plant(new PeaShooterStrategy());
            case SUNFLOWER -> new Plant(new SunflowerStrategy());
            case WALLNUT -> new Plant(new WallNutStrategy());
        };
        plant.setPosition(position);
        return plant;
    }
}
