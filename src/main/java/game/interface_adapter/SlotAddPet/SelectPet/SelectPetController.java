package game.interface_adapter.SlotAddPet.SelectPet;

import game.entity.Slot;
import game.use_case.SlotAddPet.SelectPet.SelectPetInputBoundary;
import game.use_case.SlotAddPet.SelectPet.SelectPetInputData;

/*
* The controller for the Pet Select use case.
*/
public class SelectPetController {
    
    private final SelectPetInputBoundary selectPetUseCaseInteractor;


    public SelectPetController(SelectPetInputBoundary selectPetUseCaseInteractor) {
        this.selectPetUseCaseInteractor = selectPetUseCaseInteractor;
    }

    /**
     * Executes the Select Pet Use Case.
     * @param slot the slot selected to add a pet to.
     */
    public void execute(Slot slot) {
        final SelectPetInputData selectPetInputData = new SelectPetInputData(slot);
        selectPetUseCaseInteractor.execute(selectPetInputData);
    }
}
