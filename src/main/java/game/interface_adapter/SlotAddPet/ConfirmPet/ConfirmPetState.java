package game.interface_adapter.SlotAddPet.ConfirmPet;

import game.interface_adapter.SlotAddPet.ConfirmPet.ConfirmPetViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The State information representing the user's pets when
 * selecting one for a slot.
 */
public class ConfirmPetState {
    private List<String> petNames;
    private HashMap<String, ConfirmPetViewModel> petInfoMap;
    private String selectedPetName;

    public List<String> getPetNames() { return petNames; }
    public void setPetNames(List<String> petNames) { this.petNames = petNames; }

    public HashMap<String, ConfirmPetViewModel> getPetInfoMap() { return petInfoMap; }
    public void setPetInfoMap(HashMap<String, ConfirmPetViewModel> petInfoMap) { this.petInfoMap = petInfoMap; }

    public String getSelectedPetName() { return selectedPetName; }
    public void setSelectedPetName(String selectedPetName) { this.selectedPetName = selectedPetName; }
}

