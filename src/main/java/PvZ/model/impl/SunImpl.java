package PvZ.model.impl;

import PvZ.model.api.Sun;

public class SunImpl implements Sun{
    
    private final int VALUE;

    @Override
    public void incrementSunCounter() { //prende er contatore e l'aumenta de value
        final SunCounter SunCounter = this.getSunCounter();
        SunCounter.increaseValue(this.VALUE);
    }

    
}
