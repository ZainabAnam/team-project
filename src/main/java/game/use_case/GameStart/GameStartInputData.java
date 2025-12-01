package game.use_case.GameStart;

/**
 * Input data for the Game Start use case
 */
public class GameStartInputData {

    private final String filename;

    public GameStartInputData(String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return filename;
    }
}
