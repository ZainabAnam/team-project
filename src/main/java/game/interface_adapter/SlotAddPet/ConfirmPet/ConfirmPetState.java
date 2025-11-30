package game.interface_adapter.SlotAddPet.ConfirmPet;

import game.entity.Pet;
import game.interface_adapter.SlotAddPet.ConfirmPet.ConfirmPetViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The State information representing the user's pets when
 * selecting one for a slot.
 */
public class ConfirmPetState {

    private List<Pet> petList = new ArrayList<>();
    private String selectedPetName = null;
    private String errorMessage = "";

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public String getSelectedPetName() {
        return selectedPetName;
    }

    public void setSelectedPetName(String name) {
        this.selectedPetName = name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String msg) {
        this.errorMessage = msg;
    }

}

