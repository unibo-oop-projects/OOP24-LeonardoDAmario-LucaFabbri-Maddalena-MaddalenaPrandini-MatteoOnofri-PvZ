package PvZ.model.impl;

import PvZ.model.api.Sun;

public class SunImpl implements Sun{
    
    private final int VALUE;
    private boolean alive = true;

    @Override
    public void incrementSunCounter() { //prende er contatore e l'aumenta de value
        final SunCounter sunCounter = GameModelImpl.getSunCounter(); //NOT SURE IF SUNCOUNTER IS IN THE GAMEMODEL
        sunCounter.increaseValue(this.VALUE);
        this.alive = false;
    }

    
}
