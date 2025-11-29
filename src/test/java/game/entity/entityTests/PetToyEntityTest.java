package game.entity.entityTests;

import game.entity.PetToy;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class PetToyEntityTest {

    @Test
    public void testPetToyEntity(){
        PetToy petToy = new PetToy("chewToy", 10, 5);
        assertEquals("chewToy", petToy.getName());
        assertEquals(10, petToy.getPrice());
        assertEquals(5, petToy.getAffectionIncrease());
    }
}
