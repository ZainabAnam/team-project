package game.use_case.PetCard.SellPet;

public interface SellPetOutputBoundary {
    void prepareSuccessView(SellPetOutputData outputData);
    void prepareFailView(String errorMessage);
}
