package game.use_case.GetPetFact;

public class GetPetFactOutputData {
    private final String factText;
    private final String source;
    private final boolean success;

    public GetPetFactOutputData(String factText, String source, boolean success) {
        this.factText = factText;
        this.source = source;
        this.success = success;
    }

    public String getFactText() { return factText; }
    public String getSource() { return source; }
    public boolean isSuccess() { return success; }
}
