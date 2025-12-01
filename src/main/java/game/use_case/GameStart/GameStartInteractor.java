package game.use_case.GameStart;

import game.data_access.UserDataAccessObjectInterface;
import game.entity.User;

/**
 * The Interactor for the load game use case.
 */
public class GameStartInteractor implements GameStartInputBoundary {

    private final UserDataAccessObjectInterface userDataAccessObject;
    private final GameStartOutputBoundary presenter;

    public GameStartInteractor(UserDataAccessObjectInterface userDataAccessObject, GameStartOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(GameStartInputData inputData) {

        try {
            User user;

            if (inputData.getFilename().equals("userLoadData")) {
                user = userDataAccessObject.loadUser();
            } else {
                // Load from saved file (save.json)
                user = userDataAccessObject.newUser();
            }
            final GameStartOutputData outputData = new GameStartOutputData(user);
            presenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            //presenter.("Failed to load game data: " + e.getMessage());
        }
    }
}
