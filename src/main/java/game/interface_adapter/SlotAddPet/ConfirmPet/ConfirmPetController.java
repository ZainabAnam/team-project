package game.interface_adapter.SlotAddPet.ConfirmPet;

import game.entity.Pet;
import game.entity.Slot;
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
     * @param slotID the slot to add to
     * @param petName the pet selected
    * */
    public void execute(int slotID, String petName) {
        ConfirmPetInputData confirmPetInputData = new ConfirmPetInputData(slotID, petName);
        confirmPetUseCaseInteractor.execute(confirmPetInputData);
    }
}
