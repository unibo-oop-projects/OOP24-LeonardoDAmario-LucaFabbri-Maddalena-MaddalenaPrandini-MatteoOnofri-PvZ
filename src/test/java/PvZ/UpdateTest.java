package PvZ;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import PvZ.model.api.Entities.EntitiesManager;
import PvZ.model.api.Plants.Plant;
import PvZ.model.impl.Entitities.EntitiesManagerImpl;
import PvZ.model.impl.Plants.PlantFactory;
import PvZ.utilities.Position;

public class UpdateTest {
    private final PlantFactory plantFactory;
    private final EntitiesManager entitiesManager;

    public UpdateTest() {
        this.plantFactory = new PlantFactory();
        this.entitiesManager = new EntitiesManagerImpl();
    }

    @Test
    public void testPeashooterUpdate(){
        Position position = new Position(1, 1);
        Plant peashooter = plantFactory.createPeashooter(position);

        peashooter.update(1L, entitiesManager);
        assertEquals(0, entitiesManager.getEntities().size());

        
        peashooter.update(1L, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size(), "Con deltaTime cumulato pari a 1.5 deve essere creato 1 bullet");
    
        
        peashooter.decreaseLife(peashooter.getLife());
        peashooter.update(2L, entitiesManager);
        assertEquals(1, entitiesManager.getEntities().size(), "Con vita zero non devono essere creati bullet");
    }

    @Test
    public void testSunflowerUpdate() {
        Position pos = new Position(2, 2);
        Plant sunflower = plantFactory.createSunflower(pos);
        int initialSun = entitiesManager.getSunCount();
        
        
        sunflower.update(1L, entitiesManager);
        assertEquals(initialSun, entitiesManager.getSunCount(), "Con deltaTime insufficiente non deve aumentare il numero di soli");
        
        
        sunflower.update(2L, entitiesManager);
        assertEquals(initialSun + 25, entitiesManager.getSunCount(), "Con deltaTime cumulato pari a 3 deve essere aggiunto 25 soli");
        
        
        sunflower.decreaseLife(sunflower.getLife());
        sunflower.update(3L, entitiesManager);
        assertEquals(initialSun + 25, entitiesManager.getSunCount(), "Con vita zero non devono essere aggiunti ulteriori soli");
    }

    @Test
    public void testWallnutUpdate() {
        Position pos = new Position(3, 3);
        Plant wallnut = plantFactory.createWallnut(pos);
        //TODO
    }
    
    
}
