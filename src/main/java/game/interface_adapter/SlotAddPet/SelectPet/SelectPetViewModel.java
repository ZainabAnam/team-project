package game.interface_adapter.SlotAddPet.SelectPet;

import game.interface_adapter.ViewModel;

/**
 * The View Model for the select slot use case.
 */
public class SelectPetViewModel extends ViewModel<SelectPetState> {

    public SelectPetViewModel() {
        super("Select Pet");
        setState(new SelectPetState());
    }
}
