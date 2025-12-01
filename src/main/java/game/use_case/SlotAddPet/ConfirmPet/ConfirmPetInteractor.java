package game.use_case.SlotAddPet.ConfirmPet;

import game.data_access.UserDataAccessObjectInterface;
import game.entity.Pet;
import game.entity.User;

public class ConfirmPetInteractor implements ConfirmPetInputBoundary {
    private final ConfirmPetOutputBoundary userPresenter;

    public ConfirmPetInteractor(ConfirmPetOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
        }

    @Override
    public void execute(ConfirmPetInputData confirmPetInputData) {
        //User user = UserDataAccessObjectInterface.getUser();
        //Pet pet = user.getPetByName(confirmPetInputData.getPetName());
//
//
//        if (pet == null) {
//            userPresenter.preparePetAlreadyDeployed("Pet not found.");
//            return;
//        }
//
//        // Pet Already Deployed
//        if (pet.getIsDeployed()) {
//            userPresenter.preparePetAlreadyDeployed("This Pet is already deployed in another slot.");
//        }
//        // Success
//        else {
//            confirmPetInputData.getSlot().addPet(confirmPetInputData.getPetName());
//            confirmPetInputData.getPetName().deployPet();
//            final ConfirmPetOutputData confirmPetOutputData = new ConfirmPetOutputData(confirmPetInputData.getSlot()
//                    , confirmPetInputData.getPetName());
//            userPresenter.preparePetSlotView(confirmPetOutputData);
//            }
        }

    }
