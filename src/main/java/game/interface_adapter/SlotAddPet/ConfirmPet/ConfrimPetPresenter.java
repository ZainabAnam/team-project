package game.interface_adapter.SlotAddPet.ConfirmPet;

import game.use_case.SlotAddPet.ConfirmPet.ConfirmPetOutputBoundary;
import game.use_case.SlotAddPet.ConfirmPet.ConfirmPetOutputData;

public class ConfrimPetPresenter implements ConfirmPetOutputBoundary {

    private final ConfirmPetViewModel viewModel;
    public ConfrimPetPresenter(ConfirmPetViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void preparePetSlotView(ConfirmPetOutputData outputData) {
        viewModel.setSelectedPet(outputData.getPetName());
        viewModel.setError("");
    }

    @Override
    public void preparePetAlreadyDeployed(String errorMessage) {
        viewModel.setError(errorMessage);
    }
}
