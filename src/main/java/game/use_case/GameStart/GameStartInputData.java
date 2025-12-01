package game.use_case.GameStart;

public class GameStartInputData {

    private final String filename;

    public GameStartInputData(String filename) {
        this.filename = filename;
    }

    public String getFileName() {
        return filename;
    }
}
