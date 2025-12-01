package game.use_case.GameSave;

import game.use_case.GameStart.GameStartInputData;

/**
 * Input boundary for the save game use case.
 */
public interface SaveInputBoundary {
    void execute(SaveInputData inputData);
}
