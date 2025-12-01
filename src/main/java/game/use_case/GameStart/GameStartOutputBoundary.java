package game.use_case.GameStart;

/**
 * Output boundary for the start game use case
 */
public interface GameStartOutputBoundary {

    void prepareSuccessView(GameStartOutputData data);
    void prepareFailView(String errorMessage);

}
