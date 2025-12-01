package game.interface_adapter.GameStart;

import game.use_case.GameStart.GameStartInputBoundary;
import game.use_case.GameStart.GameStartInputData;

public class GameStartController {

    private final GameStartInputBoundary loadInteractor;

    public GameStartController(GameStartInputBoundary loadInteractor) {
        this.loadInteractor = loadInteractor;
    }

    public void loadGameExecute() {
        loadInteractor.execute(new GameStartInputData("data/userLoadData.json"));
    }

    public void newGameExecute() {
        loadInteractor.execute(new GameStartInputData("data/newGameData.json"));
    }

}
