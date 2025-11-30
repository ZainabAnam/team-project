package game.use_case.SlotAddPet.ConfirmPet;

public class ConfirmPetInteractor implements ConfirmPetInputBoundary {
    private final ConfirmPetOutputBoundary userPresenter;

    public ConfirmPetInteractor(ConfirmPetOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
        }

    @Override
    public void execute(ConfirmPetInputData confirmPetInputData) {

        // Pet Tired
        if (confirmPetInputData.getPet().getIsDeployed()) {
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
