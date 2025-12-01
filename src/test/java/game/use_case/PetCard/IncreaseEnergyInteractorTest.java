package game.use_case.PetCard;

import game.entity.Pet;
import game.entity.User;

import game.use_case.PetCard.IncreaseEnergy.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for IncreaseEnergyInteractor
 */

public class IncreaseEnergyInteractorTest {
    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    IncreaseEnergyInteractor increaseEnergyInteractor = new IncreaseEnergyInteractor(dataAccess, outputBoundary);

    @Test
    public void testEnergyIncreaseSuccess() {
        User user = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setName("Max");
        user.addToPetInventory(pet);
        increaseEnergyInteractor.execute(new IncreaseEnergyInputData(pet.getName(), 1));
        assertEquals(pet.getBaseEnergy() + 1, pet.getEnergyLevel());
    }

    @Test
    public void testEnergyIncreaseFail() {
        User user = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setName("Max");
        user.addToPetInventory(pet);
        // food wasn't/couldn't be used to increase energy
        increaseEnergyInteractor.execute(new IncreaseEnergyInputData(pet.getName(), 0));
        assertEquals(pet.getBaseEnergy(), pet.getEnergyLevel());
    }

    private static class TestDataAccess implements IncreaseEnergyUserDataAccessInterface {
        private User user = new User();

        @Override
        public User getUser() {
            return user;
        }

    }

    private static class TestOutputBoundary implements IncreaseEnergyOutputBoundary {
        private IncreaseEnergyOutputData outputData;

        @Override
        public void prepareSuccessView(IncreaseEnergyOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.outputData = null;
        }
    }
}
