package PvZ.model.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import PvZ.model.api.Sun;

public class SunImpl implements Sun{
    
    private final int VALUE = 25;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    @Override
    public void incrementSunCounter() { 
        scheduler.scheduleAtFixedRate(() -> GameController.(VALUE), 5, 5, TimeUnit.SECONDS);
    }

    
}
