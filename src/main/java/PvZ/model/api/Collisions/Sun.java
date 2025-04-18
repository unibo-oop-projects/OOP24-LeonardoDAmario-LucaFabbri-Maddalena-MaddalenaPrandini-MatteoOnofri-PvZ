package PvZ.model.api.Collisions;

public interface Sun {

    boolean canIncrementSunCounter();

    int getSunValue();

    void startSunTimer();

    boolean isAlreadyWorking();
}
