package game.use_case.PetCard.RenamePet;

public class RenamePetOutputData {
    private final String oldName;
    private final String newName;
    private final boolean success;
    private final String message;

    public RenamePetOutputData(String oldName, String newName, boolean success, String message) {
        this.oldName = oldName;
        this.newName = newName;
        this.success = success;
        this.message = message;
    }

    public String getOldName() { return oldName; }
    public String getNewName() { return newName; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}
