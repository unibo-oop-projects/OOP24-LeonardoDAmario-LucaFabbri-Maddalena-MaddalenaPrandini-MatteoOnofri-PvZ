package PvZ.model.impl;

import PvZ.model.api.BasePlant;
import PvZ.utilities.Position;

public class PlantFactory {
    public static BasePlant createPlant(String type, Position position) {
        Plant plant=null;
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
       }
        plant.setPosition(position);   
        return plant;
    }
}
