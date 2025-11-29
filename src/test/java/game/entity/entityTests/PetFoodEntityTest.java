package game.entity.entityTests;

import game.entity.PetFood;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class PetFoodEntityTest {

    @Test
    public void testPetFoodEntity() {
        PetFood petFood = new PetFood("kibble", 10, 1);
        assertEquals("kibble", petFood.getName());
        assertEquals(10, petFood.getPrice());
        assertEquals(1, petFood.getEnergyIncrease());
    }
}
