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
        try {
            User user = dAO.getUser();
            user.addCoins(user.getClickBonus());
            final ManualClickerOutputData outputData = new ManualClickerOutputData(true, "", user.getCoinCount());
            userPresenter.prepareSuccessView(outputData);
        } catch (Exception e) {
            userPresenter.prepareFailView("Click error");
        }
    }
}
