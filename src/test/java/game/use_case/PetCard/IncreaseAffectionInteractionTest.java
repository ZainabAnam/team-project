package game.use_case.PetCard;

import game.Constants;
import game.entity.Pet;
import game.entity.User;

import game.use_case.PetCard.IncreaseAffection.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static game.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for IncreaseAffectionInteractor
 */

public class IncreaseAffectionInteractionTest {
    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    IncreaseAffectionInteractor increaseAffectionInteractor = new IncreaseAffectionInteractor(dataAccess, outputBoundary);

    @Test
    public void testAffectionIncreaseSuccess() {
        User user = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setName("Max");
        user.addToPetInventory(pet);
        user.addToItemList(ITEM_CHEW_TOY);
        increaseAffectionInteractor.execute(new IncreaseAffectionInputData(pet.getName(), ITEM_CHEW_TOY));
        assertEquals(INITIAL_AFFECTION_XP + PET_TOY_BASIC_AFFECTION_INCREASE, pet.getAffectionXP());
    }

    @Test
    public void testAffectionIncreaseFail() {
        User user = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setName("Max");
        user.addToPetInventory(pet);
        // toy item does not exist
        increaseAffectionInteractor.execute(new IncreaseAffectionInputData(pet.getName(), ITEM_CHEW_TOY));
        assertEquals(INITIAL_AFFECTION_XP, pet.getAffectionXP());
    }

    private static class TestDataAccess implements IncreaseAffectionUserDataAccessInterface {
        private User user = new User();

        @Override
        public User getUser() {
            return user;
        }
    }

    private static class TestOutputBoundary implements IncreaseAffectionOutputBoundary {
        private IncreaseAffectionOutputData outputData;

        @Override
        public void prepareSuccessView(IncreaseAffectionOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.outputData = null;
        }
    }
}
