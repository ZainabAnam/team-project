package game.use_case.MainScreenManualClicker;
import game.entity.User;

public class ManualClickerInteractor implements ManualClickerInputBoundary {

    private final ManualClickerUserDataAccessInterface dAO;
    private final ManualClickerOutputBoundary userPresenter;

    public ManualClickerInteractor(ManualClickerUserDataAccessInterface dAO, ManualClickerOutputBoundary userPresenter) {
        this.dAO = dAO;
        this.userPresenter = userPresenter;
    }
    @Override
    public void execute(ManualClickerInputData inputData) {
        if (!dAO.userExists(inputData.getUserID())) {
//            userPresenter.prepareFailView("Click error.");
        }
        else {
                final User user = dAO.getUser(inputData.getUserID());
                 user.addCoins(user.getClickBonus());

                 final ManualClickerOutputData outputData = new ManualClickerOutputData(user.getCoinCount());
                // userPresenter.prepareSuccessView(outputData);
            }
        }
    }
