package PvZ.model.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import PvZ.model.api.Sun;

public class SunImpl implements Sun{
    
    private static final int VALUE = 25;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean isReady = false;

    @Override
    public void startSunTimer() { 
        this.isReady = false;
        scheduler.scheduleAtFixedRate(() -> this.isReady = true, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public boolean canIncrementSunCounter() {
        return this.isReady;
    } 

    @Override
    public int getSunValue() {
        return VALUE;
    }
    
}
