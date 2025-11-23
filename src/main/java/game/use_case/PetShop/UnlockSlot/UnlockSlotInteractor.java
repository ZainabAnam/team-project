package game.use_case.PetShop.UnlockSlot;

import game.entity.User;
import game.use_case.PetShop.ShopMessageConstants;

/**
 * Interactor for unlocking slot use case.
 * This class implements the UnlockSlotInputBoundary interface and is responsible for unlocking slot.
 */
public class UnlockSlotInteractor implements UnlockSlotInputBoundary {
    
    private final UnlockSlotOutputBoundary outputBoundary;
    private final UnlockSlotDataAccessInterface dataAccess;
    
    /**
     * Constructor for UnlockSlotInteractor.
     * @param outputBoundary the presenter that will handle output
     * @param dataAccess the data access object for user data persistence
     */
    public UnlockSlotInteractor(UnlockSlotOutputBoundary outputBoundary, 
                               UnlockSlotDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }
    
    /**
     * Execute the unlock slot use case.
     * 
     * @param inputData contains the unlock request data
     */
    @Override
    public void execute(UnlockSlotInputData inputData) {
        // Get current user
        User user = dataAccess.getCurrentUser();
        
        // Check if user can unlock (not at max slots)
        if (!user.canUnlockSlot()) {
            UnlockSlotOutputData outputData = new UnlockSlotOutputData(
                false, 
                ShopMessageConstants.MAX_SLOTS_REACHED, 
                user.getUnlockedSlots(), 
                user.getUnlockedSlots()
            );
            outputBoundary.presentUnlockFailure(outputData);
            return;
        }
        
        // Get unlock price
        int unlockPrice = user.getCurrentUnlockSlotPrice();
        
        // Check if user has enough coins
        if (!user.coinCheck(unlockPrice)) {
            UnlockSlotOutputData outputData = new UnlockSlotOutputData(
                false, 
                ShopMessageConstants.INSUFFICIENT_COINS, 
                user.getUnlockedSlots(), 
                user.getUnlockedSlots()
            );
            outputBoundary.presentUnlockFailure(outputData);
            return;
        }
        
        // store the before slots for output
        int beforeSlots = user.getUnlockedSlots();
        
        // unlock
        user.unlockPetSlot();
        
        // Save the updated user data
        dataAccess.saveUser(user);
        
        // Create success output data
        UnlockSlotOutputData outputData = new UnlockSlotOutputData(
            true, 
            ShopMessageConstants.UNLOCK_SUCCESS, 
            beforeSlots, 
            user.getUnlockedSlots()
        );
        
        outputBoundary.presentUnlockSuccess(outputData);
    }
}
