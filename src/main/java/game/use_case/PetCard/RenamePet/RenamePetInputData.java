package game.use_case.PetCard.RenamePet;

public class RenamePetInputData {
    private final int petIndex;
    private final String newName;
    private final String username;

    public RenamePetInputData(int petIndex, String newName, String username) {
        this.petIndex = petIndex;
        this.newName = newName;
        this.username = username;
    }

    public int getPetIndex() { return petIndex; }
    public String getNewName() { return newName; }
    public String getUsername() { return username; }
}
