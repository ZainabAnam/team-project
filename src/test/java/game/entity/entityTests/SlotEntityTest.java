package game.entity.entityTests;

import game.entity.Slot;
import game.entity.Pet;
import org.testng.annotations.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class SlotEntityTest {

    @Test
    public void testIsUnlocked() {
        Slot slot1 = new Slot(true);
        assertTrue(slot1.isUnlocked());

        Slot slot2 = new Slot(false);
        assertFalse(slot2.isUnlocked());
    }

    @Test
    public void testSetUnlocked() {
        Slot slot = new Slot(false);
        slot.setUnlocked();
        assertTrue(slot.isUnlocked());
    }

    @Test
    public void testAddPet() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        Slot slot = new Slot(true);
        slot.addPet(pet);
        assertNotNull(slot.getPet());
    }

    @Test
    public void testRemovePet() {
        Slot slot = new Slot(true);
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        slot.addPet(pet);
        slot.removePet(pet);
        assertNull(slot.getPet());
    }

    @Test
    public void testPetTired() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 0, 6, 6,
                imageIcon);
        Slot slot = new Slot(true);
        slot.addPet(pet);
        slot.petTired(pet);
        assertTrue(slot.isPetTired());
    }
}
