package game.interface_adapter.SlotAddPet.ConfirmPet;

import game.entity.Pet;
import game.interface_adapter.ViewModel;

import java.util.List;

import static jdk.jfr.internal.periodic.PeriodicEvents.setChanged;

/*
* The View Model for the Confirm Pet View
*/
public class ConfirmPetViewModel extends ViewModel<ConfirmPetState> {
    
    private final ConfirmPetState state = new ConfirmPetState();
    
    public ConfirmPetViewModel() {
        super("Confirm Pet");
        setState(new ConfirmPetState());
    }

    public ConfirmPetState getState() {
        return state;
    }

    public void setPets(List<Pet> pets) {
        state.setPetList(pets);
        stateChanged();
    }

    public void setSelectedPet(String name) {
        state.setSelectedPetName(name);
        stateChanged();
    }

    public void setError(String error) {
        state.setErrorMessage(error);
        stateChanged();
    }

    private void stateChanged() {
        setChanged();
    }

}
