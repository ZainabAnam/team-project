package game.use_case.GameSave;

import game.use_case.GameStart.GameStartOutputData;

/**
 * Output boundary for the save game use case
 */
public interface SaveOutputBoundary {
    void prepareSuccessView(SaveOutputData data);
    void prepareFailView(String errorMessage);
}
