package game.use_case.PetCard.RenamePet;

public interface RenamePetOutputBoundary {
    void prepareSuccessView(RenamePetOutputData outputData);
    void prepareFailView(String errorMessage);
}
