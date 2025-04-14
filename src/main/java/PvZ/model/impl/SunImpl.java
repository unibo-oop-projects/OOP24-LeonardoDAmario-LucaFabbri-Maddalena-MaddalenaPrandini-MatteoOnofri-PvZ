package PvZ.model.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import PvZ.model.api.Sun;

public class SunImpl implements Sun{
    
    private static final int VALUE = 25;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean isReady = false;
    private boolean isWorking = false;

    @Override
    public void startSunTimer() { 
        this.isReady = false;
        this.isWorking = true;
        scheduler.scheduleAtFixedRate(() -> this.isReady = true, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public boolean canIncrementSunCounter() {
        if(this.isReady) {
            this.isWorking = false;
        }
        return this.isReady;
    } 

    @Override
    public int getSunValue() {
        return VALUE;
    }
    
    @Override
    public boolean isAlreadyWorking() {
        return this.isWorking;
    }
}
