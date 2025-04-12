package PvZ;

import org.junit.jupiter.api.Test;
import PvZ.model.impl.Plants.PeaShooter;
import PvZ.utilities.Position;

public class UpdateTest {
    
    @Test
    public void testPeashooterUpdate() {
        PeaShooter peaShooter = new PeaShooter();
        peaShooter.setPosition(new Position(0, 0));
        peaShooter.update(0, null);
    }
}
