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
        User user = dataAccess.getUser();
        // save user to database
        manualClickerInteractor.execute(new ManualClickerInputData());

        assertEquals(Constants.INITIAL_COINS + user.getClickBonus(), user.getCoinCount());

    }

    @Test
    public void testClickerReflection() {
        User user = dataAccess.getUser();
        user.addCoins(Constants.INITIAL_CLICKBONUS);
        assertEquals(Constants.INITIAL_COINS + user.getClickBonus(), user.getCoinCount());
    }

    private static class TestDataAccess implements ManualClickerUserDataAccessInterface {
        private User user = new User();

        @Override
        public User getUser() {
            return user;
        }

//        @Override
//        public Boolean userExists(String userID) {
//            return user != null;
//        }
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
