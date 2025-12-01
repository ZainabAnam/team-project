package game.interface_adapter.GameSave;

import game.entity.User;
import game.use_case.GameSave.SaveInputBoundary;
import game.use_case.GameSave.SaveInputData;

public class SaveController {

    private final SaveInputBoundary interactor;
    private final User user;

    public SaveController(SaveInputBoundary interactor, User user) {
        this.interactor = interactor;
        this.user = user;
    }
    public void execute() {
        interactor.execute(new SaveInputData(user));
    }
}
