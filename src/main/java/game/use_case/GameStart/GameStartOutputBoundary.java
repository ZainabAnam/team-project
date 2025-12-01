package game.use_case.GameStart;

public interface GameStartOutputBoundary {

    void prepareSuccessView(GameStartOutputData data);
    void prepareFailView(String errorMessage);

}
