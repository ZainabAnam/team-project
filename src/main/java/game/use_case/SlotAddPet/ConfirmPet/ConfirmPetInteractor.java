package game.use_case.SlotAddPet.ConfirmPet;

import game.use_case.SlotAddPet.SelectPet.SlotAddPetOutputData;

public class ConfirmPetInteractor implements ConfirmPetInputBoundary {
    private final ConfirmPetOutputBoundary userPresenter;

    public ConfirmPetInteractor(ConfirmPetOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
        }

    @Override
    public void execute(ConfirmPetInputData confirmPetInputData) {

        // Pet Tired
        if (confirmPetInputData.getPet().getEnergyLevel() == 0) {
            userPresenter.preparePetTiredView("This Pet is tired. Deploy anyway?");
        }
        // Pet Already Deployed
        else if (confirmPetInputData.getPet().getIsDeployed()) {
            userPresenter.preparePetAlreadyDeployed("This Pet is already deployed in another slot.");
        }
        // Success
        else {
            confirmPetInputData.getSlot().addPet(confirmPetInputData.getPet());
            confirmPetInputData.getPet().deployPet();
            final ConfirmPetOutputData confirmPetOutputData = new ConfirmPetOutputData(confirmPetInputData.getSlot()
                    , confirmPetInputData.getPet());
            userPresenter.preparePetSlotView(confirmPetOutputData);
            }
        }

    }
