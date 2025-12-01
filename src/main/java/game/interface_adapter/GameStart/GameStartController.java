package game.interface_adapter.GameStart;

import game.use_case.GameStart.GameStartInputBoundary;
import game.use_case.GameStart.GameStartInputData;

public class GameStartController {

    private final GameStartInputBoundary interactor;

    public GameStartController(GameStartInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void newGameExecute() {
        interactor.execute(new GameStartInputData("data/newGameData.json"));
    }

    public void loadGameExecute() {
        interactor.execute(new GameStartInputData("data/userLoadData.json"));
    }

}
