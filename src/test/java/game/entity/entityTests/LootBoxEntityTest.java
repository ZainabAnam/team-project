package game.entity.entityTests;

import game.entity.LootBox;
import game.entity.Pet;
import org.testng.annotations.Test;
import game.Constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LootBoxEntityTest {

    @Test
    public void testLootBoxEntity() {
        LootBox lootBox = new LootBox();

        assertEquals("LootBox", lootBox.getName());
        assertEquals(Constants.LOOT_BOX_PRICE, lootBox.getPrice());
        assertEquals("LootBox", lootBox.getType());
    }

    @Test
    public void testGetPet() {
        LootBox lootBox = new LootBox();
        Pet pet = lootBox.getPet();
        assertTrue(pet.getPetType().equals("Dog") || pet.getPetType().equals("Cat"));
    }
}
