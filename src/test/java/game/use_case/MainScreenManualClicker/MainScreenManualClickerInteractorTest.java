package game.use_case.MainScreenManualClicker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.entity.User;
import game.Constants;

/**
 * Test class for ManualClickerInteractor
 */

public class MainScreenManualClickerInteractorTest {

    TestDataAccess dataAccess = new TestDataAccess();
    TestOutputBoundary outputBoundary = new TestOutputBoundary();
    ManualClickerInteractor manualClickerInteractor = new ManualClickerInteractor(dataAccess, outputBoundary);

    @Test
    public void testClickSuccess() {
        User user = dataAccess.getUser("test");
        // save user to database
        manualClickerInteractor.execute(new ManualClickerInputData("test"));

        assertEquals(Constants.INITIAL_COINS + user.getClickBonus(), user.getCoinCount());

    }

    @Test
    public void testClickFail() {
        User user = dataAccess.getUser("test");
        manualClickerInteractor.execute(new ManualClickerInputData("test"));
        // user is not saved to database, so they are not found by the data access object

        assertEquals(Constants.INITIAL_COINS, user.getCoinCount());
    }

    private static class TestDataAccess implements ManualClickerUserDataAccessInterface {
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

    private static class TestOutputBoundary implements ManualClickerOutputBoundary {
        private ManualClickerOutputData outputData;

        @Override
        public void prepareSuccessView(ManualClickerOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.outputData = null;
        }
    }
}
