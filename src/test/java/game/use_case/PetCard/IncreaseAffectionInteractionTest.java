package game.use_case.PetCard;

import game.Constants;
import game.entity.Pet;
import game.entity.User;

import game.use_case.PetCard.IncreaseAffection.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;

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
        increaseAffectionInteractor.execute(new IncreaseAffectionInputData("test", "Max", 1));
    }

    @Test
    public void testAffectionIncreaseFail() {
        User user = new User();
        ImageIcon imageIcon = new ImageIcon("/images/Pet Images/Cat Images/Main Coon Icon.png");
        Pet pet = new Pet("Cat", "Maine Coon", "Elite", 7, 6, 6,
                imageIcon);
        pet.setName("Max");

    }

    private static class TestDataAccess implements IncreaseAffectionUserDataAccessInterface {
        private User user = new User();

        @Override
        public User getUser(String userID) {
            return user;
        }

        @Override
        public Boolean userExists(String userID) {
            return user != null;
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
