package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.utilities.Position;

public class PlantFactory {
    public static BasePlant createPlant(String type, Position position) {
        Plant plant;
       switch(type){
            case "PeaShooter":
                plant= new Plant(new PeaShooterStrategy());
                break;
            case "Sunflower":
                plant= new Plant(new SunflowerStrategy());
                break;  
            case "Wallnut":
                plant= new Plant(new WallNutStrategy());
                break;
            default:
                throw new IllegalArgumentException("Invalid plant type");
       }
        plant.setPosition(position);   
        return plant;
    }
}
