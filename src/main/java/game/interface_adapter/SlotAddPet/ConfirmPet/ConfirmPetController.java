package game.interface_adapter.SlotAddPet.ConfirmPet;

import game.use_case.SlotAddPet.ConfirmPet.ConfirmPetInputBoundary;
import game.use_case.SlotAddPet.ConfirmPet.ConfirmPetInputData;

/*
* The controller for the Select/Confirm Pet use case.
*/
public class ConfirmPetController {

    private final ConfirmPetInputBoundary confirmPetUseCaseInteractor;

    public ConfirmPetController(ConfirmPetInputBoundary confirmPetUseCaseInteractor) {
        this.confirmPetUseCaseInteractor = confirmPetUseCaseInteractor;
    }

    /**
    * Executes the Confirm Pet Use Case.
    * @param pet the pet selected
    * */
    public static void execute(confirmPetUseCaseInteractor);
}
