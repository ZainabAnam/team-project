package game.entity.entityTests;
import game.Constants;
import game.entity.Pet;

import org.testng.annotations.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class PetEntityTest {

    @Test
    public void testPetEntity() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);

        assertEquals("Cat", pet.getPetType());
        assertEquals("Maine Coon", pet.getPetBreed());
        assertEquals("Elite", pet.getTier());
        assertEquals(7, pet.getBaseEnergy());
        assertEquals(6, pet.getBaseClick());
        assertEquals(6, pet.getBaseRecovery());
        assertEquals(imageIcon, pet.getPetIcon());
        assertEquals(Constants.INITIAL_AFFECTION_XP, pet.getAffectionXP());
        assertEquals(Constants.INITIAL_AFFECTION_LEVEL, pet.getAffectionLevel());
        assertEquals(Constants.SELLING_BASE_PRICE, pet.getSellingPrice());
        assertFalse(pet.isDeployed());
    }

    @Test
    public void testUpgradeClickSpeed() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.upgradeClickSpeed();
        assertEquals(11, pet.getClickingSpeed());
    }

    @Test
    public void testIncreaseAffectionXP() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.increaseAffectionXP(15);
        assertEquals(Constants.INITIAL_AFFECTION_XP + 15, pet.getAffectionXP());
    }

    @Test
    public void testIncreaseEnergyLevel() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.increaseEnergyLevel(10);
        assertEquals(17, pet.getEnergyLevel());
    }

    @Test
    public void testDepleteEnergy() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.depleteEnergy();
        assertEquals(6, pet.getEnergyLevel());
    }

    @Test
    public void testDeployPet() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.deployPet();
        assertTrue(pet.isDeployed());
    }

    @Test
    public void testSetIsDeployed() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setIsDeployed(true);
        assertTrue(pet.isDeployed());
        pet.setIsDeployed(false);
        assertFalse(pet.isDeployed());
    }

    @Test
    public void testSetName() {
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setName("Kitty");
        assertEquals("Kitty", pet.getName());
    }
}
