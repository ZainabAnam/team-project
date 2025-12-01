package game.use_case.GameSave;

import game.data_access.UserDataAccessObjectInterface;
import game.entity.User;
import game.use_case.GameStart.GameStartOutputBoundary;

/**
 * The Interactor for the save game use case.
 */
public class SaveInteractor implements SaveInputBoundary{

    private final UserDataAccessObjectInterface userDataAccessObject;
    private final SaveOutputBoundary presenter;

    public SaveInteractor(UserDataAccessObjectInterface userDataAccessObject, SaveOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(SaveInputData inputData) {
        try {
            userDataAccessObject.saveUser(inputData.getUser());
            presenter.prepareSuccessView(new SaveOutputData(true));
        } catch (Exception e) {
            presenter.prepareFailView("Failed to save game.");
        }

    }
}
