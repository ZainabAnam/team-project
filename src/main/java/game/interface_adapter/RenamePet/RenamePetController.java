package game.interface_adapter.RenamePet;

import game.use_case.PetCard.RenamePet.RenamePetInputBoundary;
import game.use_case.PetCard.RenamePet.RenamePetInputData;

public class RenamePetController {
    private final RenamePetInputBoundary renamePetUseCaseInteractor;

    public RenamePetController(RenamePetInputBoundary renamePetUseCaseInteractor) {
        this.renamePetUseCaseInteractor = renamePetUseCaseInteractor;
    }

    public void execute(int petIndex, String newName, String username) {
        RenamePetInputData inputData = new RenamePetInputData(petIndex, newName, username);
        renamePetUseCaseInteractor.execute(inputData);
    }
}
